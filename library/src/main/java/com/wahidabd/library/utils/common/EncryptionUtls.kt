package com.wahidabd.library.utils.common

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

fun bytesToHex(bytes: ByteArray): String {
    var hexChars = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
    val hexArray = hexChars
    hexChars = CharArray(bytes.size * 2)

    var size = 0
    val bytesLength = bytes.size + -1
    if (size <= bytesLength) {
        do {
            val j = size++
            val v = bytes[j].toInt() and 255
            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 15]
        } while (size <= bytesLength)
    }

    return hexChars.toString()
}

fun getApplicationSignature(): List<String> = TODO()

fun getSha256Signature(data: String): String {
    val digest = MessageDigest.getInstance("SHA-256")
    val standard = StandardCharsets.UTF_8
    val bytes = data.toByteArray(standard)
    val encodeHash = digest.digest(bytes)
    return bytesToHex(encodeHash)
}