package com.example.foodapp.data.entity

import java.io.Serializable

data class Foods(
    var cartId: Int,
    var id: Int,
    var name: String,
    var image: String,
    var price: Int,
    var orderAmount:Int,
    var category: String
//    <!--        cartId:Int,name:String,image :String,price:Int,category :String,orderAmount:Int-->
) : Serializable {
}