package dev.yjyoon.kwlibrarywearos.ui.util

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object EncryptionUtil {

    private const val SECRET_IV = "1234567812345678"

    fun String.encrypt(secretKey: String): String {
        val iv = IvParameterSpec(SECRET_IV.toByteArray())
        val keySpec = SecretKeySpec(secretKey.toByteArray(), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING").apply {
            init(Cipher.ENCRYPT_MODE, keySpec, iv)
        }
        val crypted = cipher.doFinal(this.toByteArray())
        val encodedByte = Base64.encode(crypted, Base64.DEFAULT)

        return String(encodedByte)
    }

    fun String.encode(): String = Base64.encodeToString(this.toByteArray(), Base64.DEFAULT)
}
