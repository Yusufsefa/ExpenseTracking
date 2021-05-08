package com.yyusufsefa.expensetracking.ui.onBoarding.screen

import android.os.Bundle
import android.view.View
import com.yyusufsefa.expensetracking.R
import com.yyusufsefa.expensetracking.common.BaseFragment
import com.yyusufsefa.expensetracking.databinding.FragmentFirstScreenBinding
import com.yyusufsefa.expensetracking.ui.onBoarding.ViewPagerFragment


class FirstScreenFragment :
    BaseFragment<FragmentFirstScreenBinding>(R.layout.fragment_first_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = (requireParentFragment() as ViewPagerFragment).binding.viewPager

        binding.next.setOnClickListener {
            viewPager.currentItem = 1
        }
    }
}