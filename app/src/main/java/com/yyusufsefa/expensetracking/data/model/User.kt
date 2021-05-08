package com.yyusufsefa.expensetracking.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val _id: Int? = 0,
    var name: String? = "",
    var gender: Gender? = null,
    val totalAmount: Double? = 0.0
) : Parcelable