package com.yyusufsefa.expensetracking.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.expensetracking.data.model.CurrencyConvert
import com.yyusufsefa.expensetracking.data.model.CurrencyType
import com.yyusufsefa.expensetracking.data.model.ExpenseModel
import com.yyusufsefa.expensetracking.data.model.User
import com.yyusufsefa.expensetracking.data.preferences.MyPreferences
import com.yyusufsefa.expensetracking.data.repository.CurrencyRepository
import com.yyusufsefa.expensetracking.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val myPreferences: MyPreferences
) :
    ViewModel() {

    private val _currency = MutableLiveData<CurrencyConvert>()
    val currency: LiveData<CurrencyConvert> get() = _currency

    private val _allExpenseModel = MutableLiveData<Resource<List<ExpenseModel>?>>()
    val allExpenseModel: LiveData<Resource<List<ExpenseModel>?>> get() = _allExpenseModel

    private val _totalTRY = MutableLiveData<Double?>()
    val totalTRY: LiveData<Double?> get() = _totalTRY

    private val _totalUSD = MutableLiveData<Double?>()
    val totalUSD: LiveData<Double?> get() = _totalUSD

    private val _totalEUR = MutableLiveData<Double?>()
    val totalEUR: LiveData<Double?> get() = _totalEUR

    private val _totalGBP = MutableLiveData<Double?>()
    val totalGBP: LiveData<Double?> get() = _totalGBP

    init {
        fetchAllExpenseModel()
        fetchTotalTRY()
        fetchTotalUSD()
        fetchTotalEUR()
        fetchTotalGBP()
    }

    private fun fetchTotalTRY() {
        viewModelScope.launch {
            currencyRepository.getTotalTRY().filterNotNull().collect { response ->
                response.let {
                    _totalTRY.postValue(it)
                }
            }
        }
    }

    private fun fetchTotalUSD() {
        viewModelScope.launch {
            currencyRepository.getTotalUSD().filterNotNull().collect { response ->
                response.let {
                    _totalUSD.postValue(it)
                }
            }
        }
    }

    private fun fetchTotalEUR() {
        viewModelScope.launch {
            currencyRepository.getTotalEUR().filterNotNull().collect { response ->
                response.let {
                    _totalEUR.postValue(it)
                }
            }
        }
    }

    private fun fetchTotalGBP() {
        viewModelScope.launch {
            currencyRepository.getTotalGBP().filterNotNull().collect { response ->
                response.let {
                    _totalGBP.postValue(it)
                }
            }
        }
    }

    private fun fetchAllExpenseModel() {
        viewModelScope.launch {
            _allExpenseModel.postValue(Resource.loading())
            currencyRepository.getAllExpenseModel().collect { response ->
                response.let {
                    _allExpenseModel.postValue(Resource.success(it))
                }
            }
        }
    }

    val user: LiveData<User> = currencyRepository.fetchUser()

    val prefCurrencyType = myPreferences.getCurrencyType()

    private val curr = CurrencyConvert()

    fun setCurrencyType(currencyType: CurrencyType) = myPreferences.setCurrencyType(currencyType)

    fun fetchCurrencyConverter() {
        viewModelScope.launch {
            currencyRepository.getCurrencyConvertValue("TRY_USD").let {
                curr.TRY_USD = it.value
            }
            currencyRepository.getCurrencyConvertValue("TRY_EUR").let {
                curr.TRY_EUR = it.value
            }
            currencyRepository.getCurrencyConvertValue("TRY_GBP").let {
                curr.TRY_GBP = it.value
            }
            currencyRepository.getCurrencyConvertValue("USD_TRY").let {
                curr.USD_TRY = it.value
            }
            currencyRepository.getCurrencyConvertValue("USD_EUR").let {
                curr.USD_EUR = it.value
            }
            currencyRepository.getCurrencyConvertValue("USD_GBP").let {
                curr.USD_GBP = it.value
            }
            currencyRepository.getCurrencyConvertValue("EUR_TRY").let {
                curr.EUR_TRY = it.value
            }
            currencyRepository.getCurrencyConvertValue("EUR_USD").let {
                curr.EUR_USD = it.value
            }
            currencyRepository.getCurrencyConvertValue("EUR_GBP").let {
                curr.EUR_GBP = it.value
            }
            currencyRepository.getCurrencyConvertValue("GBP_TRY").let {
                curr.GBP_TRY = it.value
            }
            currencyRepository.getCurrencyConvertValue("GBP_USD").let {
                curr.GBP_USD = it.value
            }
            currencyRepository.getCurrencyConvertValue("GBP_EUR").let {
                curr.GBP_EUR = it.value
            }
            _currency.value = curr
            currencyRepository.insertCurrencyConvert(curr)
        }
    }

}