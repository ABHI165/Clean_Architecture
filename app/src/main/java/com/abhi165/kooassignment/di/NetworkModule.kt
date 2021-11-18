package com.abhi165.kooassignment.di

import android.content.Context
import android.content.pm.PackageManager
import com.abhi165.kooassignment.data.NetworkApi
import com.abhi165.kooassignment.data.repository.PostRepositoryImpl
import com.abhi165.kooassignment.domain.repository.PostRespository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideBaseUrl(@ApplicationContext context: Context) = context.packageManager
        .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
        .metaData["base_url"].toString()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context
    ) = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, baseUrl:String) = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): NetworkApi = retrofit.create(NetworkApi::class.java)

    @Singleton
    @Provides
    fun providePostRepository(api: NetworkApi):PostRespository= PostRepositoryImpl(api)





}