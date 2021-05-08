package com.yyusufsefa.expensetracking.ui.onBoarding.screen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.yyusufsefa.expensetracking.R
import com.yyusufsefa.expensetracking.common.BaseFragment
import com.yyusufsefa.expensetracking.data.preferences.MyPreferences
import com.yyusufsefa.expensetracking.databinding.FragmentThirdScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ThirdScreenFragment :
    BaseFragment<FragmentThirdScreenBinding>(R.layout.fragment_third_screen) {

    @Inject
    lateinit var preferences: MyPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.finish.setOnClickListener {
            if (preferences.isLogin()) {
                findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_viewPagerFragment_to_editUserFragment)
            }
            preferences.setOnBoarding(true)
        }
    }

}