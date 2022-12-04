package com.example.foodapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.entity.CategoryFood
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.databinding.CardCategoryBinding
import com.example.foodapp.ui.viewmodel.MainViewModel
import java.util.*
import kotlin.collections.HashSet

class AdapterCategory (var mContext : Context, var categoryList : List<Foods>, var viewModel : MainViewModel)
    : RecyclerView.Adapter<AdapterCategory.CardCategoryHolder>() {

    inner class CardCategoryHolder(var binding: CardCategoryBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCategory.CardCategoryHolder {
        val binding : CardCategoryBinding =
            DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.card_category,parent,false)
        return CardCategoryHolder(binding)

    }
    override fun onBindViewHolder(holder: CardCategoryHolder, position: Int) {
        val food = categoryList.get(position)
        val b = holder.binding
        b.foodscat = food

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }


}