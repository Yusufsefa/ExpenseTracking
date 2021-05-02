package com.yyusufsefa.expensetracking.data.repository

import com.yyusufsefa.expensetracking.data.model.CurrencyModel
import com.yyusufsefa.expensetracking.network.Api
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val api: Api
) {

    suspend fun getCurrencyConvertValue(currencyConvert: String): CurrencyModel {
        return api.getCurrencyValue(currencyConvert, COMPACT, API_KEY)
    }

    companion object {
        private const val COMPACT = "ultra"
        private const val API_KEY = "4d08ab2969374459529f"
    }
}