package com.yyusufsefa.expensetracking.ui.onBoarding

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yyusufsefa.expensetracking.ui.onBoarding.screen.FirstScreenFragment
import com.yyusufsefa.expensetracking.ui.onBoarding.screen.SecondScreenFragment
import com.yyusufsefa.expensetracking.ui.onBoarding.screen.ThirdScreenFragment

class ViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int) = when (position) {
        0 -> FirstScreenFragment()
        1 -> SecondScreenFragment()
        2 -> ThirdScreenFragment()
        else -> ThirdScreenFragment()
    }
}