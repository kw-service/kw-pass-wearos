package dev.yjyoon.kwlibrarywearos.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.yjyoon.kwlibrarywearos.data.exception.NonAccountDataException
import dev.yjyoon.kwlibrarywearos.ui.model.User
import dev.yjyoon.kwlibrarywearos.ui.repository.LocalRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalRepository {

    override suspend fun getUserData(): Result<User> = runCatching {
        val id = dataStore.data.map { it[stringPreferencesKey(KEY_USER_ID)] }.first()
        val password = dataStore.data.map { it[stringPreferencesKey(KEY_USER_PW)] }.first()
        val phone = dataStore.data.map { it[stringPreferencesKey(KEY_USER_PHONE)] }.first()

        if (id == null || password == null || phone == null) throw NonAccountDataException
        User(id = id, password = password, phone = phone, autoSignedIn = true)
    }

    override suspend fun setUserData(user: User): Result<Unit> = runCatching {
        dataStore.edit { it[stringPreferencesKey(KEY_USER_ID)] = user.id }
        dataStore.edit { it[stringPreferencesKey(KEY_USER_PW)] = user.password }
        dataStore.edit { it[stringPreferencesKey(KEY_USER_PHONE)] = user.phone }
    }

    companion object {
        private const val KEY_USER_ID = "KEY_USER_ID"
        private const val KEY_USER_PW = "KEY_USER_PW"
        private const val KEY_USER_PHONE = "KEY_USER_PHONE"
    }
}