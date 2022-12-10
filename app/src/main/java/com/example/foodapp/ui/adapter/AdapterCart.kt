package com.example.foodapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.entity.cardmodels.FoodsCart
import com.example.foodapp.databinding.CardCartBinding
import com.example.foodapp.ui.viewmodel.CardViewModel
import com.example.foodapp.util.Constant
import com.example.foodapp.util.Constant.user_name
import com.google.android.material.snackbar.Snackbar


class AdapterCart(var mContext : Context, var foodList : List<FoodsCart>, var viewModel : CardViewModel)
    :RecyclerView.Adapter<AdapterCart.CardCartHolder>() {

    inner class CardCartHolder(var binding: CardCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardCartHolder {
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
        val orderAmount = foods.price * foods.orderAmount
        itemBinding.cartName.text = foods.name
        itemBinding.cartCategory.text = foods.category
        itemBinding.cartPrice.text = "$" + foods.price
        itemBinding.cartOrderAmount.text = "$" + orderAmount.toString()
        Glide.with(holder.binding.root.context)
            .load(Constant.BASE_IMAGE_URL + foods.image)
            .into(itemBinding.imageCart)

        itemBinding.imageViewDelete.setOnClickListener {
            Snackbar.make(it, "Do you want to delete ${foods.name} ?", Snackbar.LENGTH_LONG)
                .setAction("YES") {
                    viewModel.deleteFoods(foods.cartId, user_name)
                }.show()

        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }


}