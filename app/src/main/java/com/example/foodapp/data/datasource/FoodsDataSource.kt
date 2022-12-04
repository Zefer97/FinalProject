package com.example.foodapp.data.datasource

import com.example.foodapp.data.entity.Foods
import com.example.foodapp.retrofit.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodsDataSource( var fdao : FoodDao) {

    suspend fun loadFoods(): List<Foods> =
        withContext(Dispatchers.IO) {
            fdao.loadFood().foods
        }
//
//    suspend fun insertFoods(name :String,
//                            image :String,
//                            price :Int,
//                            category :String,
//                            orderAmount:Int,
//                            userName :String) = fdao.insertFood(name, image, price, category, orderAmount, userName)
//
//    suspend fun getFoodsCart(userName:String) = fdao.getFoodsCart(userName)
//
//    suspend fun deleteFoods(cartId : Int,
//                            userName : String) = fdao.deleteFood(cartId,userName)
}



