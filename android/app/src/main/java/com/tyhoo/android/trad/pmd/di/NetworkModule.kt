package com.tyhoo.android.trad.pmd.di

import com.tyhoo.android.trad.pmd.api.PMDService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providePMDService(): PMDService {
        return PMDService.create()
    }
}