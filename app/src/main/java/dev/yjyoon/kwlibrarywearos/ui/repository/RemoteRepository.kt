package dev.yjyoon.kwlibrarywearos.ui.repository

import dev.yjyoon.kwlibrarywearos.ui.model.User

interface RemoteRepository {

    suspend fun getQrCode(user: User): Result<String>
}
