package com.yyusufsefa.expensetracking.data.local

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yyusufsefa.expensetracking.data.model.CurrencyType
import com.yyusufsefa.expensetracking.data.model.ExpenseType
import com.yyusufsefa.expensetracking.data.model.Gender
import java.lang.reflect.Type

object DataTypeConverter {

    @androidx.room.TypeConverter
    @JvmStatic
    fun currencyTypeToString(item: CurrencyType?) = Gson().toJson(item)

    @androidx.room.TypeConverter
    @JvmStatic
    fun stringToCurrencyType(value: String?): CurrencyType? {
        if (value == null) {
            return null
        }
        val type: Type = object : TypeToken<CurrencyType?>() {}.type
        return Gson().fromJson<CurrencyType?>(value, type)
    }

    @androidx.room.TypeConverter
    @JvmStatic
    fun expenseTypeToString(item: ExpenseType?) = Gson().toJson(item)

    @androidx.room.TypeConverter
    @JvmStatic
    fun stringToExpenseType(value: String?): ExpenseType? {
        if (value == null) {
            return null
        }
        val type: Type = object : TypeToken<ExpenseType?>() {}.type
        return Gson().fromJson<ExpenseType?>(value, type)
    }

    @androidx.room.TypeConverter
    @JvmStatic
    fun genderToString(item: Gender?) = Gson().toJson(item)

    @androidx.room.TypeConverter
    @JvmStatic
    fun stringToGender(value: String?): Gender? {
        if (value == null) {
            return null
        }
        val type: Type = object : TypeToken<Gender?>() {}.type
        return Gson().fromJson<Gender?>(value, type)
    }
}
