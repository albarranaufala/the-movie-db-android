package com.example.screenplay.di

import com.example.screenplay.data.source.ScreenplayRepository
import com.example.screenplay.data.source.remote.RemoteDataSource
import com.example.screenplay.data.source.remote.retrofit.NetworkConfig

object Injection {
    fun provideRepository(): ScreenplayRepository {
        val remoteDataSource = RemoteDataSource.getInstance(NetworkConfig())

        return ScreenplayRepository.getInstance(remoteDataSource)
    }
}