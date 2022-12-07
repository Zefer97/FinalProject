package com.example.foodapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.entity.CategoryFood
import com.example.foodapp.databinding.CardCategoryBinding

class AdapterCategory(
    val mContext: Context,
    val categoryList: List<CategoryFood>,
    val mSelectedCategoryId: String
) : RecyclerView.Adapter<AdapterCategory.CardCategoryHolder>() {

    var selectedCategoryId: String = mSelectedCategoryId

    var categoryItemClickListener: ((String)-> Unit)? = null


    inner class CardCategoryHolder(var binding: CardCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterCategory.CardCategoryHolder {
        val binding: CardCategoryBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.card_category,
                parent,
                false
            )
        return CardCategoryHolder(binding)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CardCategoryHolder, position: Int) {
        val categoryItemModel = categoryList[position]
        val categoryBinding = holder.binding
        categoryBinding.categoryName.text = if(categoryItemModel.categoryName == selectedCategoryId) " Selected " + categoryItemModel.categoryName else " Not Selected " + categoryItemModel.categoryName
        categoryBinding.root.setOnClickListener {
            selectedCategoryId = categoryItemModel.categoryName
            categoryItemClickListener?.invoke(categoryItemModel.categoryName)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }


}