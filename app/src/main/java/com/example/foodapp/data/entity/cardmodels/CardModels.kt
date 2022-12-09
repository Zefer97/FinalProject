package com.example.foodapp.data.entity.cardmodels


import com.google.gson.annotations.SerializedName

data class CardModels(
    @SerializedName("foods_cart")
    val foodsCart: List<FoodsCart>
)

data class FoodsCart(
    @SerializedName("cartId")
    val cartId: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("orderAmount")
    val orderAmount: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("userName")
    val userName: String
)