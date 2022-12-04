package com.example.foodapp.retrofit

import retrofit2.create

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getFoodDao() : FoodDao {
            return RetrofitClient.getClient(BASE_URL).create(FoodDao::class.java)
        }
    }
}