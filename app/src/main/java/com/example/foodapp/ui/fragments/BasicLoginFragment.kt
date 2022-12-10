package com.example.foodapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentBasicLoginBinding
import com.example.foodapp.util.go
import kotlinx.android.synthetic.main.fragment_basic_login.*
import kotlinx.android.synthetic.main.fragment_basic_login.view.*

class BasicLoginFragment : Fragment() {
    private lateinit var binding : FragmentBasicLoginBinding

    private val sharedPreference by lazy {
        context?.getSharedPreferences("Login", Context.MODE_PRIVATE)
    }

    private fun updateUserEnterCount() {
        val editor = sharedPreference?.edit()
        editor?.putInt("userEnterTime", 1)
        editor?.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedPreference?.getInt("userEnterTime", 0)?.let {
            if (it > 1) {
                findNavController().navigate(R.id.action_basicLoginFragment_to_mainFragment)
            } else {
                updateUserEnterCount()
            }
        } ?: run {

            updateUserEnterCount()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_basic_login, container, false)

        binding.LoginButton.setOnClickListener {
            val username = editTextUserName.text.toString().trim()
            val password = pass.text.toString().trim()
           if (username == "elcin" && password == "12345"){
               Navigation.go(it,R.id.action_basicLoginFragment_to_mainFragment)
           }else Toast.makeText(requireContext(),"Wrong Username or Password",Toast.LENGTH_SHORT).show()

        }
        return binding.root
    }
    }