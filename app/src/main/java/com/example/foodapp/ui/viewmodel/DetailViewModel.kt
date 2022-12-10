package com.example.foodapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.entity.CRUDResponse
import com.example.foodapp.data.repo.FoodsRepository
import com.example.foodapp.util.Constant.user_name
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (var foodRepo: FoodsRepository) : ViewModel() {

    val insertFoodResponse: MutableLiveData<CRUDResponse> = MutableLiveData<CRUDResponse>()
    fun insertFood(name:String,image :String,price:Int,category :String,orderAmount:Int){
        CoroutineScope(Dispatchers.Main).launch{
            insertFoodResponse.value = foodRepo.insertFood(name, image,
                price, category,
                orderAmount , user_name)
        }
    }
    val insertFoodWishResponse: MutableLiveData<CRUDResponse> = MutableLiveData<CRUDResponse>()
    fun insertFoodWish(name:String,image :String,price:Int,category :String,orderAmount:Int){
        CoroutineScope(Dispatchers.Main).launch{
            insertFoodResponse.value = foodRepo.insertFoodWish(name, image,
                price, category,
                orderAmount , "zafar")
        }
    }

}