package com.example.foodapp.data.repo

import com.example.foodapp.data.datasource.FoodsRemoteDataSource
import com.example.foodapp.data.entity.CRUDResponse
import com.example.foodapp.data.entity.Foods

class FoodsRepository(var foodsRemoteDataSource: FoodsRemoteDataSource) {

    suspend fun loadFoods(): List<Foods> = foodsRemoteDataSource.loadFoods()

    suspend fun insertFood(
        name: String,
        image: String,
        price: Int,
        category: String,
        orderAmount: Int,
        userName: String
    ): CRUDResponse =
        foodsRemoteDataSource.insertFoods(name, image, price, category, orderAmount, userName)
    suspend fun insertFoodWish(
        name: String,
        image: String,
        price: Int,
        category: String,
        orderAmount: Int,
        userName: String
    ): CRUDResponse =
        foodsRemoteDataSource.insertFoodsWish(name, image, price, category, orderAmount, "zafar")
    suspend fun deleteFoods(cartId : Int, userName : String)= foodsRemoteDataSource.deleteFoods(cartId, userName)
    suspend fun getFoodsCart(userName : String) = foodsRemoteDataSource.getFoodsCart(userName)
    suspend fun getWish(userName: String) = foodsRemoteDataSource.getWish("zafar")


}