package dev.yjyoon.kwlibrarywearos.ui.util

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

object QrCodeUtil {

    private val barcodeEncoder = BarcodeEncoder()

    fun String.convertToQrCode(size: Int = 400): Bitmap =
        barcodeEncoder.encodeBitmap(this, BarcodeFormat.QR_CODE, size, size)
}
