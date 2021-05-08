package com.yyusufsefa.expensetracking.ui.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.expensetracking.R
import com.yyusufsefa.expensetracking.common.BaseFragment
import com.yyusufsefa.expensetracking.data.model.CurrencyType
import com.yyusufsefa.expensetracking.data.model.ExpenseModel
import com.yyusufsefa.expensetracking.data.model.Gender
import com.yyusufsefa.expensetracking.databinding.FragmentHomeBinding
import com.yyusufsefa.expensetracking.ui.adapter.ExpenseAdapter
import com.yyusufsefa.expensetracking.util.Status
import com.yyusufsefa.expensetracking.util.networkShowToast
import com.yyusufsefa.expensetracking.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    private var expenseAdapter = ExpenseAdapter()

    private var listExpenseModel: List<ExpenseModel>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addSpendFragment)
        }

        binding.homeUserCardView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_editUserFragment)
        }

        if (isInternetAvailable(requireContext())) {
            viewModel.fetchCurrencyConverter()
        } else {
            requireContext().networkShowToast(getString(R.string.connection_err_msg))
        }
        initUser()
        initUI()
        hideSwipeFloatAction()
        checkChipState()
    }

    private fun initUser() {
        viewModel.user.observe(viewLifecycleOwner, {
            when (it.gender) {
                Gender.MALE -> binding.txtUserName.text = getString(R.string.gender_male, it.name)
                Gender.FEMALE -> binding.txtUserName.text =
                    getString(R.string.gender_female, it.name)
                Gender.IDLE -> binding.txtUserName.text = getString(R.string.gender_idle, it.name)
            }
        })
    }

    private fun initUI() {
        val lastType = viewModel.prefCurrencyType.toString()
        binding.rcycHome.adapter = expenseAdapter
        viewModel.allExpenseModel.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> requireContext().showToast("Loading...")
                Status.SUCCESS -> {
                    listExpenseModel = it.data
                    prefObserveChip(lastType, listExpenseModel!!)
                }
                Status.ERROR -> requireContext().showToast(it.message.toString())
            }
        })
        expenseAdapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailSpendFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun checkChipState() {
        binding.chipGroup.setOnCheckedChangeListener { _, checkId ->
            when (checkId) {
                R.id.chip_TL -> {
                    listExpenseModel?.map { it.currencyType = CurrencyType.TRY }
                    expenseAdapter.submitList(listExpenseModel)
                    viewModel.totalTRY.observe(viewLifecycleOwner, {
                        binding.txtTotalAccount.text = getString(R.string.currency_TL, it)
                    })
                    expenseAdapter.notifyDataSetChanged()
                    viewModel.setCurrencyType(CurrencyType.TRY)
                }
                R.id.chip_DOLAR -> {
                    listExpenseModel?.map { it.currencyType = CurrencyType.USD }
                    expenseAdapter.submitList(listExpenseModel)
                    viewModel.totalUSD.observe(viewLifecycleOwner, {
                        binding.txtTotalAccount.text = getString(R.string.currency_USD, it)
                    })
                    expenseAdapter.notifyDataSetChanged()
                    viewModel.setCurrencyType(CurrencyType.USD)
                }
                R.id.chip_EURO -> {
                    listExpenseModel?.map { it.currencyType = CurrencyType.EUR }
                    expenseAdapter.submitList(listExpenseModel)
                    viewModel.totalEUR.observe(viewLifecycleOwner, {
                        binding.txtTotalAccount.text = getString(R.string.currency_EUR, it)
                    })
                    expenseAdapter.notifyDataSetChanged()
                    viewModel.setCurrencyType(CurrencyType.EUR)
                }
                R.id.chip_STERLIN -> {
                    listExpenseModel?.map { it.currencyType = CurrencyType.GBP }
                    expenseAdapter.submitList(listExpenseModel)
                    viewModel.totalGBP.observe(viewLifecycleOwner, {
                        binding.txtTotalAccount.text = getString(R.string.currency_GBP, it)
                    })
                    expenseAdapter.notifyDataSetChanged()
                    viewModel.setCurrencyType(CurrencyType.GBP)
                }
            }
        }
    }

    private fun hideSwipeFloatAction() {
        binding.rcycHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    if (binding.floatAddButton.isShown) binding.floatAddButton.hide()
                } else {
                    if (!binding.floatAddButton.isShown) binding.floatAddButton.show()
                }
            }
        })
    }

    private fun prefObserveChip(chipTye: String, listExpenseModel: List<ExpenseModel>) =
        when (chipTye) {
            CurrencyType.TRY.toString() -> {
                binding.chipTL.isChecked = true
                expenseAdapter.submitList(listExpenseModel)
            }
            CurrencyType.USD.toString() -> {
                binding.chipDOLAR.isChecked = true
                expenseAdapter.submitList(listExpenseModel)
            }
            CurrencyType.EUR.toString() -> {
                binding.chipEURO.isChecked = true
                expenseAdapter.submitList(listExpenseModel)
            }
            CurrencyType.GBP.toString() -> {
                binding.chipSTERLIN.isChecked = true
                expenseAdapter.submitList(listExpenseModel)
            }
            else -> binding.chipTL.isChecked = true
        }

    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }
        return result
    }
}