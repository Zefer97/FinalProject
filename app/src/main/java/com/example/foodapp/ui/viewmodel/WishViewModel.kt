package com.example.foodapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.entity.cardmodels.FoodsCart
import com.example.foodapp.data.repo.FoodsRepository
import com.example.foodapp.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishViewModel @Inject constructor(var foodRepo: FoodsRepository) : ViewModel() {

    var foodList = MutableLiveData<List<FoodsCart>>()


    fun getWish(userName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodRepo.getWish("zafar").foodsCart.let {
                foodList.value = it
            }
        }
    }

    fun clearUiData() {
        foodList.value = listOf()
    }

    fun deleteFoods(cartId : Int, userName : String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodRepo.deleteFoods(cartId,"zafar")
            getWish("zafar")
        }
    }
}