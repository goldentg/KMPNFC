package com.kmp.kmpnfc

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Build
import androidx.annotation.RequiresApi

class NFCManager(private val context: Context) {

    private var nfcAdapter: NfcAdapter? = NfcAdapter.getDefaultAdapter(context)
    private var pendingIntent: PendingIntent? = null
    private var intentFiltersArray: Array<IntentFilter>? = null

    init {
        if (nfcAdapter != null) {
            pendingIntent = PendingIntent.getActivity(
                context, 0,
                Intent(context, context.javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
            )
            val ndef = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
            try {
                ndef.addDataType("text/plain")
            } catch (e: IntentFilter.MalformedMimeTypeException) {
                throw RuntimeException("Check your mime type.")
            }
            intentFiltersArray = arrayOf(ndef)
        }
    }

    fun enableNFC() {
        nfcAdapter?.enableForegroundDispatch(
            context as android.app.Activity,
            pendingIntent,
            intentFiltersArray,
            null
        )
    }

    fun disableNFC() {
        nfcAdapter?.disableForegroundDispatch(context as android.app.Activity)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun readNFC(intent: Intent): String? {
        val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
        val ndef = Ndef.get(tag)
        ndef?.connect()
        val message = ndef?.ndefMessage
        ndef?.close()
        return message?.toByteArray()?.let { String(it) }
    }
}
