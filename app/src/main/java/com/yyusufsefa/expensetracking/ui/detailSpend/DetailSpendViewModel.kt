package com.yyusufsefa.expensetracking.ui.detailSpend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.expensetracking.data.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailSpendViewModel @Inject constructor(private val repository: CurrencyRepository) :
    ViewModel() {
    fun deleteExpenseModel(id: Int) {
        viewModelScope.launch {
            repository.deleteExpenseModel(id)
        }
    }
}