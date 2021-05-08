package com.yyusufsefa.expensetracking.ui.onBoarding

import android.os.Bundle
import android.view.View
import com.yyusufsefa.expensetracking.R
import com.yyusufsefa.expensetracking.common.BaseFragment
import com.yyusufsefa.expensetracking.databinding.FragmentViewPagerBinding
import com.yyusufsefa.expensetracking.ui.onBoarding.screen.FirstScreenFragment
import com.yyusufsefa.expensetracking.ui.onBoarding.screen.SecondScreenFragment
import com.yyusufsefa.expensetracking.ui.onBoarding.screen.ThirdScreenFragment

class ViewPagerFragment : BaseFragment<FragmentViewPagerBinding>(R.layout.fragment_view_pager) {

    private val fragmentList by lazy {
        arrayListOf(
            FirstScreenFragment(),
            SecondScreenFragment(),
            ThirdScreenFragment()
        )
    }

    private val adapter by lazy {
        ViewPagerAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter
    }
}