package com.yyusufsefa.expensetracking.ui.onBoarding.screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.yyusufsefa.expensetracking.R
import com.yyusufsefa.expensetracking.common.BaseFragment
import com.yyusufsefa.expensetracking.databinding.FragmentThirdScreenBinding

class ThirdScreenFragment :
    BaseFragment<FragmentThirdScreenBinding>(R.layout.fragment_third_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.finish.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
            onBoardingFinished()
        }
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}