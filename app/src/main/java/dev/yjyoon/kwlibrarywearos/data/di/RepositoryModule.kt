package dev.yjyoon.kwlibrarywearos.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yjyoon.kwlibrarywearos.data.repository.LocalRepositoryImpl
import dev.yjyoon.kwlibrarywearos.data.repository.RemoteRepositoryImpl
import dev.yjyoon.kwlibrarywearos.ui.repository.LocalRepository
import dev.yjyoon.kwlibrarywearos.ui.repository.RemoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindLocalRepository(repo: LocalRepositoryImpl): LocalRepository

    @Binds
    @Singleton
    fun bindRemoteRepository(repo: RemoteRepositoryImpl): RemoteRepository
}
