package com.example.foodapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.databinding.CardItemBinding
import com.example.foodapp.util.Constant.BASE_IMAGE_URL

class AdapterItem(var mContext: Context) :
    RecyclerView.Adapter<AdapterItem.CardItemHolder>() {

    private var mItemList: ArrayList<Foods> = arrayListOf()

    var foodItemClickListener: ((Foods)-> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setList(itemList: List<Foods>){
        mItemList.clear()
        mItemList.addAll(itemList)
        notifyDataSetChanged()
    }

    inner class CardItemHolder(var binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemHolder {
        val binding: CardItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.card_item,
                parent,
                false
            )
        return CardItemHolder(binding)

    }

    override fun onBindViewHolder(holder: CardItemHolder, position: Int) {
        val foodItemModel = mItemList[position]
        val itemBinding = holder.binding
        itemBinding.root.setOnClickListener {
            foodItemClickListener?.invoke(foodItemModel)
        }
        itemBinding.foods = foodItemModel

        Glide.with(holder.binding.root.context)
            .load(BASE_IMAGE_URL + foodItemModel.image)
            .into(itemBinding.imageItem)
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }


}