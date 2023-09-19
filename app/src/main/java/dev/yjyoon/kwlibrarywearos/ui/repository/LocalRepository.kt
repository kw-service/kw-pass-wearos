package dev.yjyoon.kwlibrarywearos.ui.repository

import dev.yjyoon.kwlibrarywearos.ui.model.User

interface LocalRepository {

    suspend fun getUserData(): Result<User>

    suspend fun setUserData(user: User): Result<Unit>
}
