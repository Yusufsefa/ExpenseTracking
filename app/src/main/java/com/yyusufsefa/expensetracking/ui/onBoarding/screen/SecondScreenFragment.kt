package com.yyusufsefa.expensetracking.ui.onBoarding.screen

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.yyusufsefa.expensetracking.R
import com.yyusufsefa.expensetracking.common.BaseFragment
import com.yyusufsefa.expensetracking.databinding.FragmentSecondScreenBinding

class SecondScreenFragment :
    BaseFragment<FragmentSecondScreenBinding>(R.layout.fragment_second_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.next.setOnClickListener {
            viewPager?.currentItem = 2
        }
    }
}