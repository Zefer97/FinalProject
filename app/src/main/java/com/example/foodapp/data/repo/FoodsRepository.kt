package com.example.foodapp.data.repo

import com.example.foodapp.data.datasource.FoodsRemoteDataSource
import com.example.foodapp.data.entity.Foods

class FoodsRepository(var foodsRemoteDataSource: FoodsRemoteDataSource) {

    suspend fun loadFoods(): List<Foods> = foodsRemoteDataSource.loadFoods()

//    suspend fun insertFood(name :String,
//                           image :String,
//                           price :Int,
//                           category :String,
//                           orderAmount:Int,
//                           userName :String)= tds.insertFoods(name, image, price, category, orderAmount, userName)
//
//    suspend fun deleteFood(cartId : Int,
//                           userName : String)= tds.deleteFoods(cartId, userName)
//    suspend fun getFoodsCart(userName : String) = tds.getFoodsCart(userName)


}