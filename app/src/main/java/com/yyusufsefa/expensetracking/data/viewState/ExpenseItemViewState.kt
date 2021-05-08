package com.yyusufsefa.expensetracking.data.viewState

import com.yyusufsefa.expensetracking.data.model.CurrencyType
import com.yyusufsefa.expensetracking.data.model.ExpenseModel

class ExpenseItemViewState(
    private val expenseModel: ExpenseModel
) {

    fun getSpendName() = expenseModel.name.orEmpty()

    fun setCurrencyTypePrice(currencyType: CurrencyType) = when (currencyType) {
        CurrencyType.TRY -> "${expenseModel.tryPrice} ₺"
        CurrencyType.USD -> "${expenseModel.usdPrice} $"
        CurrencyType.EUR -> "${expenseModel.eurPrice} €"
        CurrencyType.GBP -> "${expenseModel.gbpPrice} £"
    }

    fun getExpenseModel() = expenseModel

    fun getImageIcon() = expenseModel.type

}