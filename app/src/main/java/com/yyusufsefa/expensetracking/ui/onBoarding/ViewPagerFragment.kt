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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf(
            FirstScreenFragment(),
            SecondScreenFragment(),
            ThirdScreenFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
    }
}