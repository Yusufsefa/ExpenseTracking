package com.yyusufsefa.expensetracking.ui.onBoarding

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yyusufsefa.expensetracking.common.BaseFragment

class ViewPagerAdapter(
    list: ArrayList<BaseFragment<out ViewDataBinding>>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    private val fragmnetList = list

    override fun getItemCount(): Int = fragmnetList.size

    override fun createFragment(position: Int): Fragment = fragmnetList[position]

}