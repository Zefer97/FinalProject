package com.example.foodapp.data.datasource

import com.example.foodapp.data.entity.CRUDResponse
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.data.services.FoodService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodsRemoteDataSource(var foodService : FoodService) {

    suspend fun loadFoods(): List<Foods> =
        withContext(Dispatchers.IO) {
            foodService.loadFood().foods
        }
    //
    suspend fun insertFoods(name :String,
                            image :String,
                            price :Int,
                            category :String,
                            orderAmount:Int,
                            userName :String): CRUDResponse = foodService.insertFood(name, image, price, category, orderAmount, userName)
//
//    suspend fun getFoodsCart(userName:String) = fdao.getFoodsCart(userName)
//
//    suspend fun deleteFoods(cartId : Int,
//                            userName : String) = fdao.deleteFood(cartId,userName)
}



