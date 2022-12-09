package com.example.foodapp.data.services

import com.example.foodapp.data.entity.CRUDResponse
import com.example.foodapp.data.entity.CardResponse
import com.example.foodapp.data.entity.FoodResponse
import com.example.foodapp.data.entity.cardmodels.CardModels
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface FoodService {
    //images/meatball.png
    //foods/insertFood.php
    //foods/deleteFood.php
    //foods/getFoodsCart.php
    @GET("foods/getAllFoods.php")
    suspend fun loadFood(): FoodResponse

    @POST("foods/insertFood.php")
    @FormUrlEncoded
    suspend fun insertFood(
        @Field("name") name: String,
        @Field("image") image: String,
        @Field("price") price: Int,
        @Field("category") category: String,
        @Field("orderAmount") orderAmount: Int,
        @Field("userName") userName: String
    ): CRUDResponse

//    @POST("foods/deleteFood.php")
//    @FormUrlEncoded
//    suspend fun deleteFood(cartId : Int, userName : String) : CRUDResponse
//

    @POST("foods/getFoodsCart.php")
    @FormUrlEncoded
    suspend fun getFoodsCart(@Field("userName") userName: String): CardModels

}