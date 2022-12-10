package com.example.submissionandroidfundamental.data.module

import com.example.submissionandroidfundamental.data.api.ApiClient
import com.example.submissionandroidfundamental.data.repository.RepositoryImplementation
import com.example.submissionandroidfundamental.domain.repo.Repository
import com.example.submissionandroidfundamental.utils.network.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class Module {
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(apiClient: ApiClient): Repository {
        return RepositoryImplementation(apiClient)
    }
}