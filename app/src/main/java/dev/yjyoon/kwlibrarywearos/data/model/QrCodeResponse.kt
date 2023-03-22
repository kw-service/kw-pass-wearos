package dev.yjyoon.kwlibrarywearos.data.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "root")
data class QrCodeResponse(
    @Element(name = "item")
    val item: QrCodeItem
)

@Xml(name = "item")
data class QrCodeItem(
    @PropertyElement(name = "qr_code")
    val qrCode: String
)