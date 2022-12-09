package com.example.foodapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentDetailBinding
import com.example.foodapp.ui.viewmodel.DetailViewModel
import com.example.foodapp.util.Constant.BASE_IMAGE_URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.detailFragment = this

        binding.imageViewBack.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.toMaininDetail)

        }
        binding.imageViewCard.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.toCardInDetail)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: DetailFragmentArgs by navArgs()
        val resultFoods = bundle.foodItemModel

        Glide.with(binding.root.context)
            .load(BASE_IMAGE_URL + bundle.foodItemModel.image)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .into(binding.imageFood)


        binding.foods = resultFoods
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun insertFood(cartId:Int,name:String,image :String,price:Int,category :String,orderAmount:Int){
        viewModel.insertFood(cartId,name,image, price, category, orderAmount)
    }
}