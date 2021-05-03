package com.yyusufsefa.expensetracking.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.expensetracking.data.model.CurrencyConvert
import com.yyusufsefa.expensetracking.data.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val currencyRepository: CurrencyRepository) :
    ViewModel() {

    private val _currency = MutableLiveData<CurrencyConvert>()
    val currency: LiveData<CurrencyConvert> get() = _currency

    private val curr = CurrencyConvert()

    init {
        fetchCurrencyConverter()
    }

    private fun fetchCurrencyConverter() {
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
        }
    }

}