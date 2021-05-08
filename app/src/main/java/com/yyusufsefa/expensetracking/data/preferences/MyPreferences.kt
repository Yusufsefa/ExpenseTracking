package com.yyusufsefa.expensetracking.data.preferences

import android.content.SharedPreferences
import com.yyusufsefa.expensetracking.data.model.CurrencyType
import javax.inject.Inject

class MyPreferences @Inject constructor(private val preferences: SharedPreferences) {
    fun getCurrencyType() = preferences.getString(TYPE_TAG, CurrencyType.TRY.toString())

    fun setCurrencyType(currencyType: CurrencyType) =
        when (currencyType) {
            CurrencyType.TRY -> {
                preferences.edit().putString(TYPE_TAG, CurrencyType.TRY.toString())
            }
            CurrencyType.EUR -> {
                preferences.edit().putString(TYPE_TAG, CurrencyType.EUR.toString())
            }
            CurrencyType.USD -> {
                preferences.edit().putString(TYPE_TAG, CurrencyType.USD.toString())
            }
            CurrencyType.GBP -> {
                preferences.edit().putString(TYPE_TAG, CurrencyType.GBP.toString())
            }
            else -> preferences.edit().putString(TYPE_TAG, CurrencyType.TRY.toString())
        }.apply()

    fun isLogin() = preferences.getBoolean(LOGIN, false)

    fun setLogined(isLogin: Boolean) = preferences.edit().putBoolean(LOGIN, isLogin).apply()

    fun getOnBoarding() = preferences.getBoolean(ONBOARDING, false)

    fun setOnBoarding(isFinis: Boolean) = preferences.edit().putBoolean(ONBOARDING, isFinis).apply()

    companion object {
        const val TYPE_TAG = "currencyConvert"
        const val LOGIN = "isLogin"
        const val ONBOARDING = "isOnBoarding"
    }
}