package com.example.foodapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.databinding.FragmentMainBinding
import com.example.foodapp.ui.adapter.AdapterCategory
import com.example.foodapp.ui.adapter.AdapterItem
import com.example.foodapp.ui.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding : FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    private val adapterFoodItem by lazy {
        AdapterItem(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        this.viewModel = viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)

        viewModel.foodList.observe(viewLifecycleOwner){
            adapterFoodItem.setList(
                if(viewModel.selectedCategoryId == "All"){
                    it
                } else {
                    viewModel.getItemListByCategoryId(viewModel.selectedCategoryId)
                }
            )

            binding.adapterItem = adapterFoodItem

            val adapterCategory = AdapterCategory(requireContext(),it,viewModel)
            binding.adapterCategory = adapterCategory

            val categoryList: HashSet<String> = hashSetOf()

            it.forEach { foodItem ->
                categoryList.add(foodItem.category)
            }
        }

        binding.recyclerViewItem.layoutManager = GridLayoutManager(context,1)

        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)


        viewModel.error.observe(viewLifecycleOwner){
            Snackbar.make(
                requireContext(),binding.rootView,it,Snackbar.LENGTH_SHORT
            ).show()
        }

        return binding.root
    }


}