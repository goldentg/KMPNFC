package com.kmp.kmpnfc

import platform.CoreNFC.*
import platform.Foundation.NSData
import platform.darwin.NSObject

class NFCManager : NSObject(), NFCNDEFReaderSessionDelegateProtocol {

    private var session: NFCNDEFReaderSession? = null

    fun enableNFC() {
        session = NFCNDEFReaderSession(
            delegate = this,
            queue = null,
            invalidateAfterFirstRead = false
        )
        session?.beginSession()
    }

    fun disableNFC() {
        session?.invalidateSession()
        session = null
    }

    fun readNFC(): String? {
        // This method will be called when a tag is detected
        return null
    }

    override fun readerSession(
        session: NFCNDEFReaderSession,
        didInvalidateWithError: NSError
    ) {
        // Handle session invalidation
    }

    override fun readerSession(
        session: NFCNDEFReaderSession,
        didDetectNDEFs: List<*>
    ) {
        for (ndef in didDetectNDEFs) {
            if (ndef is NFCNDEFMessage) {
                for (record in ndef.records) {
                    val payload = record.payload
                    val text = NSString.create(payload.bytes, payload.length.toULong(), NSUTF8StringEncoding)
                    // Process the text
                }
            }
        }
    }
}
