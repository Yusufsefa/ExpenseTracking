package com.yyusufsefa.expensetracking.network

import com.yyusufsefa.expensetracking.data.model.CurrencyModel
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("api/v7/convert")
    suspend fun getCurrencyValue(
        @Query("q") convertTpye: String,
        @Query("compact") compact: String,
        @Query("apiKey") apiKey: String
    ): CurrencyModel
}