package com.example.foodapp.data.services


import com.example.foodapp.data.entity.FoodResponse
import retrofit2.http.GET


interface FoodService {
    //images/meatball.png
    //foods/insertFood.php
    //foods/deleteFood.php
    //foods/getFoodsCart.php
    @GET("foods/getAllFoods.php")
    suspend fun loadFood() : FoodResponse

//    @POST("foods/insertFood.php")
//    @FormUrlEncoded
//    suspend fun insertFood(name :String, image :String,
//                           price :Int, category :String,
//                           rderAmount:Int, userName :String) : CRUDResponse

//    @POST("foods/deleteFood.php")
//    @FormUrlEncoded
//    suspend fun deleteFood(cartId : Int, userName : String) : CRUDResponse
//
//    @POST("foods/getFoodsCart.php")
//    @FormUrlEncoded
//    suspend fun getFoodsCart(userName :String) : CRUDResponse

}