package com.yyusufsefa.expensetracking.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "currency_convert")
data class CurrencyConvert(
    @PrimaryKey(autoGenerate = true) val _id: Long? = 0,
    var TRY_USD: Double? = 0.0,
    var TRY_EUR: Double? = 0.0,
    var TRY_GBP: Double? = 0.0,
    var USD_TRY: Double? = 0.0,
    var USD_EUR: Double? = 0.0,
    var USD_GBP: Double? = 0.0,
    var GBP_TRY: Double? = 0.0,
    var GBP_EUR: Double? = 0.0,
    var GBP_USD: Double? = 0.0,
    var EUR_TRY: Double? = 0.0,
    var EUR_USD: Double? = 0.0,
    var EUR_GBP: Double? = 0.0
) : Parcelable



