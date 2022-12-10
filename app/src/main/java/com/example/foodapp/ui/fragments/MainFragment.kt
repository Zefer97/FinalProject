package com.example.foodapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.MainActivity
import com.example.foodapp.R
import com.example.foodapp.data.entity.CategoryFood
import com.example.foodapp.databinding.FragmentMainBinding
import com.example.foodapp.ui.adapter.AdapterCategory
import com.example.foodapp.ui.adapter.AdapterItem
import com.example.foodapp.ui.viewmodel.MainViewModel
import com.example.foodapp.util.go
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    private val adapterFoodItem by lazy {
        AdapterItem(requireContext())
    }

    private var adapterFoodCategory: AdapterCategory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        this.viewModel = viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.recyclerViewItem.layoutManager = GridLayoutManager(context, 1)
        binding.recyclerViewCategory.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)


        runLiveDataObserver()

        adapterFoodItem.foodItemClickListener = { mFoodItem ->
            findNavController().navigate(
                MainFragmentDirections.toDetail(mFoodItem)
            )
        }
        binding.goCard.setOnClickListener {
            Navigation.go(it,R.id.action_mainFragment_to_cardFragment)

        }
        binding.goWish.setOnClickListener {
            Navigation.go(it,R.id.action_mainFragment_to_wishListFragment)
        }

        return binding.root
    }

    private fun runLiveDataObserver() {
        viewModel.foodList.observe(viewLifecycleOwner) {

            adapterFoodItem.setList(
                viewModel.getItemListByCategoryId(viewModel.selectedCategoryId)
            )

            if (adapterFoodCategory == null) {
                val categoryList: ArrayList<CategoryFood> = arrayListOf()
                categoryList.add(
                    CategoryFood(
                        categoryName = viewModel.selectedCategoryId
                    )
                )
                it.forEach { foodItem ->
                    categoryList.add(
                        CategoryFood(
                            categoryName = foodItem.category
                        )
                    )
                }

                adapterFoodCategory =
                    AdapterCategory(
                        requireContext(),
                        categoryList.toSet().toList(),
                        viewModel.selectedCategoryId
                    )
                adapterFoodCategory?.categoryItemClickListener = { selectedCategoryName ->
                    adapterFoodItem.setList(
                        viewModel.getItemListByCategoryId(selectedCategoryName)
                    )
                    adapterFoodItem.updateSelectedCategoryId(selectedCategoryName)
                }

            }

            binding.adapterItem = adapterFoodItem
            binding.adapterCategory = adapterFoodCategory

        }
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(
                requireContext(), binding.rootView, it, Snackbar.LENGTH_SHORT
            ).show()
        }
    }


}