package com.example.foodapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.databinding.FragmentMainBinding
import com.example.foodapp.ui.adapter.AdapterCategory
import com.example.foodapp.ui.adapter.AdapterItem
import com.example.foodapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding : FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)
        binding.mainFragment = this

        viewModel.foodList.observe(viewLifecycleOwner){
            val adapterItem = AdapterItem(requireContext(),it,viewModel)
            binding.adapterItem = adapterItem
        }
        binding.recyclerViewItem.layoutManager = GridLayoutManager(context,1)

        viewModel.foodList.observe(viewLifecycleOwner){
            val adapterCategory = AdapterCategory(requireContext(),it,viewModel)
            binding.adapterCategory = adapterCategory
        }
        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ViewModel: MainViewModel by viewModels()
        viewModel = ViewModel
    }
}