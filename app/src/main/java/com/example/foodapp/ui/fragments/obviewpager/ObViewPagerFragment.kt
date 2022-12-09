package com.example.foodapp.ui.fragments.obviewpager

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.foodapp.MainActivity
import com.example.foodapp.R
import com.example.foodapp.ui.adapter.ObViewPagerAdapter
import com.example.foodapp.ui.fragments.obviewpager.screens.OnBoardingFirstFragment
import com.example.foodapp.ui.fragments.obviewpager.screens.OnBoardingSecondFragment
import com.example.foodapp.ui.fragments.obviewpager.screens.OnBoardingThreeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_obviewpager.*

@AndroidEntryPoint
class ObViewPagerFragment : Fragment() {

    private val sharedPreference by lazy {
        context?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
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
            if (it > 0) {
                findNavController().navigate(R.id.action_onBoardingFragment_to_mainFragment)
            } else {
                updateUserEnterCount()
            }
        } ?: run {

            updateUserEnterCount()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_obviewpager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf<Fragment>(
            OnBoardingFirstFragment(),
            OnBoardingSecondFragment(),
            OnBoardingThreeFragment()
        )
        val adapter = ObViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        onBoardingViewPager.adapter = adapter

        onBoardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        mainBtn.text = "Next"
                    }
                    1 -> {
                        mainBtn.text = "Next"
                    }
                    2 -> {
                        mainBtn.text = "Finish"
                    }
                }
            }
        })

        mainBtn.setOnClickListener {
            when (onBoardingViewPager.currentItem) {
                0 -> {
                    onBoardingViewPager.currentItem = 1
                }
                1 -> {
                    onBoardingViewPager.currentItem = 2
                }
                2 -> {
                    findNavController().navigate(R.id.action_onBoardingFragment_to_mainFragment)
                }
            }
        }

    }
}

