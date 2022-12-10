package com.example.foodapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.entity.cardmodels.FoodsCart
import com.example.foodapp.databinding.CardWishlistBinding
import com.example.foodapp.ui.viewmodel.WishViewModel
import com.example.foodapp.util.Constant
import com.example.foodapp.util.Constant.user_name
import com.google.android.material.snackbar.Snackbar


class AdapterWish(var mContext : Context, var foodList : List<FoodsCart>, var viewModel : WishViewModel)
    :RecyclerView.Adapter<AdapterWish.CardWishHolder>() {

    inner class CardWishHolder(var binding : CardWishlistBinding ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardWishHolder {
        val binding: CardWishlistBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.card_wishlist,
                parent,
                false
            )
        return CardWishHolder(binding)
    }

    override fun onBindViewHolder(holder: CardWishHolder, position: Int) {
        val foods = foodList[position]
        val itemBinding = holder.binding
        itemBinding.wishName.text = foods.name
        itemBinding.wishCategory.text = foods.category
        itemBinding.wishPrice.text = "$" + foods.price
        Glide.with(holder.binding.root.context)
            .load(Constant.BASE_IMAGE_URL + foods.image)
            .into(itemBinding.imageWish)

        itemBinding.imageViewWishDelete.setOnClickListener {
            Snackbar.make(it, "Do you want to delete ${foods.name} ?", Snackbar.LENGTH_LONG)
                .setAction("YES") {
                    viewModel.deleteFoods(foods.cartId, "zafar")
                }.show()

        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }


}