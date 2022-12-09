package com.example.foodapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.databinding.CardCartBinding
import com.example.foodapp.ui.viewmodel.CardViewModel


class AdapterCart(var mContext : Context, var foodList : List<Foods>, var viewModel : CardViewModel)
    :RecyclerView.Adapter<AdapterCart.CardCartHolder>(){

    inner class CardCartHolder(var binding: CardCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CardCartHolder {
        val binding: CardCartBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.card_cart,
                parent,
                false
            )
        return CardCartHolder(binding)
    }

    override fun onBindViewHolder(holder: CardCartHolder, position: Int) {
        val foods = foodList[position]
        val itemBinding = holder.binding

        itemBinding.cartName.text = "Salam"
    }

    override fun getItemCount(): Int {
        return foodList.size
    }


}