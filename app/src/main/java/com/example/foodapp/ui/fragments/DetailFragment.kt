package com.example.foodapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
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
import com.example.foodapp.util.go
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    var orderAmount = 1

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

        viewModel.insertFoodResponse.observe(viewLifecycleOwner) {
            if(it.success == 1){
                Toast.makeText(requireContext(),"Successfully",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(),"Error happening. Please try again",Toast.LENGTH_SHORT).show()
            }
        }
        binding.button.setOnClickListener {
            viewModel.insertFood(
                name  = resultFoods.name,
                image = resultFoods.image,
                price = resultFoods.price,
                category = resultFoods.category,
                orderAmount = getOrderAmountValue()
            )
        }
        binding.imageViewWish.setOnClickListener {
            viewModel.insertFoodWish(
                name  = resultFoods.name,
                image = resultFoods.image,
                price = resultFoods.price,
                category = resultFoods.category,
                orderAmount = getOrderAmountValue()
            )
        }


        orderAmount = 1

        binding.imageViewAdd.setOnClickListener {
            orderAmount++
            binding.orderAmountTextView.text = orderAmount.toString()
        }
        binding.imageViewRemove.setOnClickListener {
            if (orderAmount >1){
                orderAmount--
                binding.orderAmountTextView.text = orderAmount.toString()
            }else{
                binding.orderAmountTextView.text = orderAmount.toString()
            }
        }

    }

    private fun getOrderAmountValue(): Int {
        return try {
            binding.orderAmountTextView.text.toString().toInt()
        } catch (e: Exception){
            1
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel
    }
}