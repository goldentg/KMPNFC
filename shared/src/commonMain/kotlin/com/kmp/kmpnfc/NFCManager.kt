package com.kmp.kmpnfc

interface NFCManager {
    fun enableNFC()
    fun disableNFC()
    fun readNFC(): String?
}
