package com.example.foodapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.entity.CategoryFood
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var frepo : FoodsRepository) : ViewModel() {

    var foodList = MutableLiveData<List<Foods>>()


    init {
        loadFoods()
    }

    fun loadFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = frepo.loadFoods()
        }
    }
    fun loadImage(){
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = frepo.loadFoods()
        }
    }
}