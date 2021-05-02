package com.yyusufsefa.expensetracking.data.model

data class ExpenseModel(
    val name: String? = null,
    val type: ExpenseType? = null,
    val price: Double? = 0.0,
    val currencyType: CurrencyType? = null,
    val usdPrice: Double = 0.0,
    val tryPrice: Double = 0.0,
    val gbpPrice: Double = 0.0,
    val eurPrice: Double = 0.0
)