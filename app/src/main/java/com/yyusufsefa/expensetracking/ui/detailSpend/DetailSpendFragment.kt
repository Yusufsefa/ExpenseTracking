package com.yyusufsefa.expensetracking.ui.detailSpend

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yyusufsefa.expensetracking.R
import com.yyusufsefa.expensetracking.common.BaseFragment
import com.yyusufsefa.expensetracking.data.viewState.DetailSpendViewState
import com.yyusufsefa.expensetracking.databinding.FragmentDetailSpendBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailSpendFragment :
    BaseFragment<FragmentDetailSpendBinding>(R.layout.fragment_detail_spend) {

    private val viewModel: DetailSpendViewModel by viewModels()

    private val navArgs by navArgs<DetailSpendFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservation()

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailSpendFragment_to_homeFragment)
        }

        binding.btnDetailDelete.setOnClickListener {
            deleteExpenseModel()
            findNavController().navigate(R.id.action_detailSpendFragment_to_homeFragment)
            Toast.makeText(requireContext(), "Silindi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initObservation() {
        binding.itemviewState = navArgs.expenseModel?.let { DetailSpendViewState(it) }
    }

    private fun deleteExpenseModel() {
        navArgs.expenseModel?._id?.let { viewModel.deleteExpenseModel(it) }
    }
}