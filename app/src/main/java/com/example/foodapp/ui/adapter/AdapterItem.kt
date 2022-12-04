package com.example.foodapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.databinding.CardItemBinding
import com.example.foodapp.ui.viewmodel.MainViewModel

class AdapterItem (var mContext : Context, var foodList : List<Foods>, var viewModel : MainViewModel)
    :RecyclerView.Adapter<AdapterItem.CardItemHolder>(){

    inner class CardItemHolder(var binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemHolder {
        val binding :CardItemBinding  =
            DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.card_item,parent,false)
//        fun showImage(image : List<Foods>){
//            var url = "http://kasimadalan.pe.hu/foods/images/$image"
//            Glide.with(mContext).load(url).override(95,84).into(binding.imageItem)
//        }
        return CardItemHolder(binding)

    }
    override fun onBindViewHolder(holder: CardItemHolder, position: Int) {
        val food = foodList.get(position)
        val b = holder.binding
       var url = food.image
       var food_url = "http://kasimadalan.pe.hu/images/$url"
        b.foods = food
//         Glide.with(mContext)
//            .load(food_url)
//            .into(b.imageItem)
        b.imageItem.setImageResource(
            mContext.resources.getIdentifier(food.image,"$food_url"
                ,mContext.packageName)
        )
        fun showImage(image : List<Foods>){
            var url = "http://kasimadalan.pe.hu/foods/images/$image"
            Glide.with(mContext).load(url).into(b.imageItem)
        }
    }

    override fun getItemCount(): Int {
       return foodList.size
    }



}