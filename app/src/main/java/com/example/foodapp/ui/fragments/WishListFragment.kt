package com.example.foodapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentWishListBinding
import com.example.foodapp.ui.adapter.AdapterWish
import com.example.foodapp.ui.viewmodel.WishViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment : Fragment() {
    private lateinit var binding : FragmentWishListBinding
    private lateinit var viewModel: WishViewModel

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: WishViewModel by viewModels()
        this.viewModel = viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_wish_list, container, false)
        binding.wishfragment = this

        viewModel.foodList.observe(viewLifecycleOwner){
            val adapterWish = AdapterWish(requireContext(),it,viewModel)
            binding.adapterWish = adapterWish
//            it.forEach(
//
//            )
        }
        binding.recyclerViewWish.layoutManager = GridLayoutManager(context,1)

        binding.imageViewBackHome.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_wishListFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getWish("zafar")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearUiData()
    }
}