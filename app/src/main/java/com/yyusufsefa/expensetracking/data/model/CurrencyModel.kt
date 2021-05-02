package com.yyusufsefa.expensetracking.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName(
        "TRY_USD",
        alternate = [
            "TRY_EUR",
            "TRY_GBP",
            "USD_TRY",
            "USD_EUR",
            "USD_GBP",
            "GBP_TRY",
            "GBP_EUR",
            "GBP_USD",
            "EUR_TRY",
            "EUR_USD",
            "EUR_GBP"
        ]
    )
    val value: Double = 0.0
)