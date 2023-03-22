package dev.yjyoon.kwlibrarywearos.data.source

import dev.yjyoon.kwlibrarywearos.data.model.AuthKeyResponse
import dev.yjyoon.kwlibrarywearos.data.model.QrCodeResponse
import dev.yjyoon.kwlibrarywearos.data.model.SecretKeyResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("mobile/MA/xml_user_key.php")
    suspend fun getSecretKey(@Field("user_id") userId: String): SecretKeyResponse

    @FormUrlEncoded
    @POST("mobile/MA/xml_login_and.php")
    suspend fun getAuthKey(
        @Field("real_id") realId: String,
        @Field("rid") rid: String,
        @Field("device_gb") deviceGb: String,
        @Field("tel_no") telNo: String,
        @Field("pass_wd") passWd: String
    ): AuthKeyResponse

    @FormUrlEncoded
    @POST("mobile/MA/xml_userInfo_auth.php")
    suspend fun getQrCode(
        @Field("real_id") realId: String,
        @Field("auth_key") authKey: String,
        @Field("new_check") newCheck: String
    ): QrCodeResponse
}
