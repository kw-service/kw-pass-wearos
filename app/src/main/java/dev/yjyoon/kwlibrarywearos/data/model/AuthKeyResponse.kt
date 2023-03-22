package dev.yjyoon.kwlibrarywearos.data.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "root")
data class AuthKeyResponse(
    @Element(name = "item")
    val item: AuthKeyItem
)

@Xml(name = "item")
data class AuthKeyItem(
    @PropertyElement(name = "auth_key")
    val authKey: String
)