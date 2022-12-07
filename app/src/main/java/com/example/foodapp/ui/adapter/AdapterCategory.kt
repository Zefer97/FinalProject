package com.example.foodapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.entity.CategoryFood
import com.example.foodapp.databinding.CardCategoryBinding
import kotlinx.android.synthetic.main.card_category.view.*

class AdapterCategory(
    private val mContext: Context,
    private val categoryList: List<CategoryFood>,
    mSelectedCategoryId: String
) : RecyclerView.Adapter<AdapterCategory.CardCategoryHolder>() {

    var selectedCategoryId: String = mSelectedCategoryId

    var categoryItemClickListener: ((String) -> Unit)? = null


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
        categoryBinding.categoryName.text = categoryItemModel.categoryName
        categoryBinding.categoryName.setTextColor(
            ContextCompat.getColor(
                mContext,
                if (categoryItemModel.categoryName == selectedCategoryId) R.color.red else R.color.black
            )
        )
        categoryBinding.imageView.setImageResource(
            mContext.resources.getIdentifier(categoryItemModel.categoryName.toLowerCase(), "drawable", mContext.packageName)
        )

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