package com.yyusufsefa.expensetracking.ui.addSpend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.expensetracking.data.model.CurrencyConvert
import com.yyusufsefa.expensetracking.data.model.CurrencyType
import com.yyusufsefa.expensetracking.data.model.ExpenseModel
import com.yyusufsefa.expensetracking.data.preferences.MyPreferences
import com.yyusufsefa.expensetracking.data.repository.CurrencyRepository
import com.yyusufsefa.expensetracking.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSpendViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val myPreferences: MyPreferences
) : ViewModel() {

    private val _currencyConvert = MutableLiveData<Resource<CurrencyConvert?>>()
    val currencyConvert: LiveData<Resource<CurrencyConvert?>> get() = _currencyConvert

    init {
        getCurrencyConvert()
    }

    fun insertExpense(expenseModel: ExpenseModel) {
        viewModelScope.launch {
            currencyRepository.insertExpense(expenseModel)
        }
    }

    private fun getCurrencyConvert() {
        viewModelScope.launch {
            _currencyConvert.postValue(Resource.loading())
            currencyRepository.getCurrencyConvert().collect { response ->
                response.let {
                    _currencyConvert.postValue(Resource.success(it))
                }
            }
        }
    }

    fun setPreferencesCurrencyType(currencyType: CurrencyType) {
        myPreferences.setCurrencyType(currencyType)
    }

}