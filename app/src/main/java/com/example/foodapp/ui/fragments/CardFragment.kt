package com.example.foodapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentCardBinding
import com.example.foodapp.ui.adapter.AdapterCart
import com.example.foodapp.ui.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardFragment : Fragment() {
    private lateinit var binding : FragmentCardBinding
    private lateinit var viewModel: CardViewModel

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        val ViewModel: CardViewModel by viewModels()
        viewModel = ViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_card, container, false)
        binding.cartFragment = this

        viewModel.foodList.observe(viewLifecycleOwner){
            val adapterCart = AdapterCart(requireContext(),it,viewModel)
            binding.adapterCart = adapterCart
        }
        binding.recyclerViewCart.layoutManager = GridLayoutManager(context,1)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFoodsCart("elcin")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearUiData()
    }
}