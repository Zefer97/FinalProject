package com.example.foodapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodapp.base.BaseViewModel
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var foodRepo: FoodsRepository
) : BaseViewModel() {

    private var _foodList = MutableLiveData<List<Foods>>()
    var foodList: LiveData<List<Foods>> = _foodList

    var selectedCategoryId: String = "All"

    init {
        loadFoods()
    }

    private fun loadFoods() {
        viewModelScope.launch {
            val response = safeNetworkOperation {
                foodRepo.loadFoods()
            }
            response?.let {
                _foodList.value = it
            }
        }
    }

    fun getItemListByCategoryId(mSelectedCategoryId: String): List<Foods> {
        selectedCategoryId = mSelectedCategoryId
        return if (mSelectedCategoryId == "All") _foodList.value
            ?: listOf() else _foodList.value?.filter { it.category == mSelectedCategoryId }
            ?: listOf()
    }

}