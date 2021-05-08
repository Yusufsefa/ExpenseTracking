package com.yyusufsefa.expensetracking.data.viewState

import com.yyusufsefa.expensetracking.data.model.ExpenseModel

class DetailSpendViewState(private val expenseModel: ExpenseModel) {

    fun getSpendName() = expenseModel.name.orEmpty()

    fun getExpenseModel() = expenseModel

    fun getImageIcon() = expenseModel.type
}