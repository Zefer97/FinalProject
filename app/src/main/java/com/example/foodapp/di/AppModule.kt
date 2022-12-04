package com.example.foodapp.di

import com.example.foodapp.data.datasource.FoodsDataSource
import com.example.foodapp.data.repo.FoodsRepository
import com.example.foodapp.retrofit.ApiUtils
import com.example.foodapp.retrofit.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providePersonRepository(tds:FoodsDataSource) : FoodsRepository {
        return FoodsRepository(tds)
    }

    @Provides
    @Singleton
    fun providePersonDatasource(fdao:FoodDao) : FoodsDataSource {
        return FoodsDataSource(fdao)
    }
    @Provides
    @Singleton
    fun provideFoodDao() : FoodDao {
        return ApiUtils.getFoodDao()
    }
}