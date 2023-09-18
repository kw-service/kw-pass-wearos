package dev.yjyoon.kwlibrarywearos.data.repository

import dev.yjyoon.kwlibrarywearos.data.exception.FailedToSignInException
import dev.yjyoon.kwlibrarywearos.data.source.ApiService
import dev.yjyoon.kwlibrarywearos.data.util.EncryptionUtil.encode
import dev.yjyoon.kwlibrarywearos.data.util.EncryptionUtil.encrypt
import dev.yjyoon.kwlibrarywearos.ui.model.User
import dev.yjyoon.kwlibrarywearos.ui.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteRepository {

    override suspend fun getQrCode(user: User): Result<String> = runCatching {
        val rid = user.id.encode()
        val realId = "0${user.id}".encode()
        val secretKey = apiService.getSecretKey(userId = realId).item.secKey
        val authKey = apiService.getAuthKey(
            realId = realId,
            rid = rid,
            deviceGb = "A",
            telNo = user.phone,
            passWd = user.password.encrypt(secretKey)
        ).item.authKey
        val qrCode = apiService.getQrCode(
            realId = realId,
            authKey = authKey,
            newCheck = "Y"
        ).item.qrCode

        qrCode.ifBlank { throw FailedToSignInException }
    }
}
