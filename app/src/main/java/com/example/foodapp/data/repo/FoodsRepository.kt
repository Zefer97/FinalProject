package com.example.foodapp.data.repo

import com.example.foodapp.data.datasource.FoodsDataSource
import com.example.foodapp.data.entity.FoodResponse
import com.example.foodapp.data.entity.Foods

class FoodsRepository (var tds : FoodsDataSource) {

    suspend fun  loadFoods() : List<Foods> = tds.loadFoods()

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