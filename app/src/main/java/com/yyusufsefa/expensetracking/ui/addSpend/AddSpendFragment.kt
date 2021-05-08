package com.yyusufsefa.expensetracking.ui.addSpend

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yyusufsefa.expensetracking.R
import com.yyusufsefa.expensetracking.common.BaseFragment
import com.yyusufsefa.expensetracking.data.model.CurrencyConvert
import com.yyusufsefa.expensetracking.data.model.CurrencyType
import com.yyusufsefa.expensetracking.data.model.ExpenseModel
import com.yyusufsefa.expensetracking.data.model.ExpenseType
import com.yyusufsefa.expensetracking.databinding.FragmentAddSpendBinding
import com.yyusufsefa.expensetracking.util.Status
import com.yyusufsefa.expensetracking.util.getAsDouble
import com.yyusufsefa.expensetracking.util.showToast
import com.yyusufsefa.expensetracking.util.validateAndDo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSpendFragment : BaseFragment<FragmentAddSpendBinding>(R.layout.fragment_add_spend) {

    private val viewModel: AddSpendViewModel by viewModels()
    private val expenseModel by lazy { ExpenseModel() }
    private var currencyConvert = CurrencyConvert()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.setOnClickListener { onSaveClick() }
        initObservation()
    }

    private fun onSaveClick() {
        validateAndDo(
            listOf(
                binding.txtAddName,
                binding.txtAddPrice
            )
        ) {
            validateInputAndRun {
                viewModel.insertExpense(getExpenseModelFromUi())
                findNavController().navigate(R.id.action_addSpendFragment_to_homeFragment)
                expenseModel.currencyType?.let { viewModel.setPreferencesCurrencyType(it) }
                requireContext().showToast("Eklendi")
            }
        }
    }

    private fun validateInputAndRun(block: () -> Unit) {
        binding.let {
            when {
                it.txtAddName.text.isNullOrEmpty() -> requireContext().showToast(getString(R.string.add_desc_error_msg))
                it.txtAddPrice.text.isNullOrEmpty() -> requireContext().showToast(getString(R.string.add_price_error_msg))
                (it.radioGroupExpenseType.checkedRadioButtonId <= 0) -> requireContext().showToast(
                    getString(R.string.radio_button_currency_type_error)
                )
                (it.radioGroupCurrencyType.checkedRadioButtonId <= 0) -> requireContext().showToast(
                    getString(R.string.radio_button_currency_type_error)
                )
                else -> block()
            }
        }
    }

    private fun initObservation() {
        viewModel.currencyConvert.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> requireContext().showToast("Harcama Ekleme")
                Status.SUCCESS -> currencyConvert = it.data!!
                Status.ERROR -> requireContext().showToast(it.message.toString())
            }
        })
    }

    private fun getExpenseModelFromUi(): ExpenseModel {
        expenseModel.apply {
            name = binding.txtAddName.text.toString()
            price = binding.txtAddPrice.text.toString().toDouble()
        }
        when {
            binding.radioButtonTL.isChecked -> {
                expenseModel.currencyType = CurrencyType.TRY
                expenseModel.tryPrice = binding.txtAddPrice.getAsDouble()
                expenseModel.eurPrice =
                    binding.txtAddPrice.getAsDouble() * (currencyConvert.TRY_EUR!!)
                expenseModel.usdPrice =
                    binding.txtAddPrice.getAsDouble() * (currencyConvert.TRY_USD!!)
                expenseModel.gbpPrice =
                    binding.txtAddPrice.getAsDouble() * (currencyConvert.TRY_GBP!!)
            }
            binding.radioButtonEUR.isChecked -> {
                expenseModel.currencyType = CurrencyType.EUR
                expenseModel.eurPrice = binding.txtAddPrice.getAsDouble()
                expenseModel.tryPrice =
                    binding.txtAddPrice.getAsDouble() * (currencyConvert.EUR_TRY!!)
                expenseModel.usdPrice =
                    binding.txtAddPrice.getAsDouble() * (currencyConvert.EUR_USD!!)
                expenseModel.gbpPrice =
                    binding.txtAddPrice.getAsDouble() * (currencyConvert.EUR_GBP!!)
            }
            binding.radioButtonUSD.isChecked -> {
                expenseModel.currencyType = CurrencyType.USD
                expenseModel.usdPrice = binding.txtAddPrice.getAsDouble()
                expenseModel.tryPrice =
                    binding.txtAddPrice.getAsDouble() * (currencyConvert.USD_TRY!!)
                expenseModel.eurPrice =
                    binding.txtAddPrice.getAsDouble() * (currencyConvert.USD_EUR!!)
                expenseModel.gbpPrice =
                    binding.txtAddPrice.getAsDouble() * (currencyConvert.USD_GBP!!)
            }
            binding.radioButtonGBP.isChecked -> {
                expenseModel.currencyType = CurrencyType.GBP
                expenseModel.gbpPrice = binding.txtAddPrice.getAsDouble()
                expenseModel.usdPrice =
                    binding.txtAddPrice.getAsDouble() * (currencyConvert.GBP_USD!!)
                expenseModel.tryPrice =
                    binding.txtAddPrice.getAsDouble() * (currencyConvert.TRY_GBP!!)
                expenseModel.eurPrice =
                    binding.txtAddPrice.getAsDouble() * (currencyConvert.GBP_EUR!!)
            }
        }
        when {
            binding.radioButtonBill.isChecked -> expenseModel.type = ExpenseType.BILL
            binding.radioButtonRent.isChecked -> expenseModel.type = ExpenseType.RENT
            binding.radioButtonOther.isChecked -> expenseModel.type = ExpenseType.OTHER
        }
        return expenseModel
    }
}