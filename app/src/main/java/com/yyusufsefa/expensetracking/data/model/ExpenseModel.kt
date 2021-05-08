package com.yyusufsefa.expensetracking.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "expense_model")
data class ExpenseModel(
    @PrimaryKey(autoGenerate = true) val _id: Int = 0,
    var name: String? = null,
    var type: ExpenseType? = null,
    var price: Double? = 0.0,
    var currencyType: CurrencyType? = null,
    var usdPrice: Double = 0.0,
    var tryPrice: Double = 0.0,
    var gbpPrice: Double = 0.0,
    var eurPrice: Double = 0.0
) : Parcelable