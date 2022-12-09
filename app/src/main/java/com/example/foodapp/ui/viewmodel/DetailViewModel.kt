package com.example.foodapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodapp.data.repo.FoodsRepository
import com.example.foodapp.util.Constant.userName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (var foodRepo: FoodsRepository) : ViewModel() {

    fun insertFood(cartId:Int,name:String,image :String,price:Int,category :String,orderAmount:Int){
        CoroutineScope(Dispatchers.Main).launch{
            foodRepo.insertFood(cartId,name, image,
                price, category,
                orderAmount , userName)
        }
    }

}