package com.yyusufsefa.expensetracking.di

import android.content.Context
import android.content.SharedPreferences
import com.yyusufsefa.expensetracking.data.preferences.MyPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            "currencyConvert", Context.MODE_PRIVATE
        )

    @Singleton
    @Provides
    fun provideSessionManager(preferences: SharedPreferences) =
        MyPreferences(preferences)
}


