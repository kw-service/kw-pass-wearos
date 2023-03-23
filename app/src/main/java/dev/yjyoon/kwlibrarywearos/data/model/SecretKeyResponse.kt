package dev.yjyoon.kwlibrarywearos.data.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "root")
data class SecretKeyResponse(
    @Element(name = "item")
    val item: SecretKeyItem
)

@Xml(name = "item")
data class SecretKeyItem(
    @PropertyElement(name = "sec_key")
    val secKey: String
)