package com.yyusufsefa.expensetracking.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yyusufsefa.expensetracking.data.model.CurrencyConvert
import com.yyusufsefa.expensetracking.data.model.ExpenseModel
import com.yyusufsefa.expensetracking.data.model.User

@Database(
    entities = [CurrencyConvert::class, ExpenseModel::class, User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataTypeConverter::class)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}