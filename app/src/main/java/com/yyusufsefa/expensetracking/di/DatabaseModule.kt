package com.yyusufsefa.expensetracking.di

import android.content.Context
import androidx.room.Room
import com.yyusufsefa.expensetracking.data.local.ExpenseDao
import com.yyusufsefa.expensetracking.data.local.ExpenseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun expenseDatabase(@ApplicationContext context: Context): ExpenseDatabase =
        Room.databaseBuilder(
            context,
            ExpenseDatabase::class.java,
            "game_database"
        ).build()

    @Provides
    @Singleton
    fun gameDao(expenseDatabase: ExpenseDatabase): ExpenseDao =
        expenseDatabase.expenseDao()
}