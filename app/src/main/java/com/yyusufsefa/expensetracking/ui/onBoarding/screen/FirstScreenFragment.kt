package com.yyusufsefa.expensetracking.ui.onBoarding.screen

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.yyusufsefa.expensetracking.R
import com.yyusufsefa.expensetracking.common.BaseFragment
import com.yyusufsefa.expensetracking.databinding.FragmentFirstScreenBinding


class FirstScreenFragment :
    BaseFragment<FragmentFirstScreenBinding>(R.layout.fragment_first_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.next.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }
}