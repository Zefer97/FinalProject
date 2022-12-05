package com.example.foodapp.di

import com.example.foodapp.data.datasource.FoodsRemoteDataSource
import com.example.foodapp.data.repo.FoodsRepository
import com.example.foodapp.data.services.FoodService
import com.example.foodapp.util.Constant.BASE_API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideFoodRepository(
        foodsRemoteDataSource: FoodsRemoteDataSource
    ): FoodsRepository {
        return FoodsRepository(foodsRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideFoodRemoteDatasource(
        foodService: FoodService
    ): FoodsRemoteDataSource {
        return FoodsRemoteDataSource(foodService)
    }

    @Singleton
    @Provides
    fun provideFoodServices(
        retrofit: Retrofit,
    ): FoodService {
        return retrofit.create(FoodService::class.java)
    }


}