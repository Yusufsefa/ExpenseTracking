package com.yyusufsefa.expensetracking.data.repository

import androidx.lifecycle.LiveData
import com.yyusufsefa.expensetracking.BuildConfig
import com.yyusufsefa.expensetracking.data.local.ExpenseDao
import com.yyusufsefa.expensetracking.data.model.CurrencyConvert
import com.yyusufsefa.expensetracking.data.model.CurrencyModel
import com.yyusufsefa.expensetracking.data.model.ExpenseModel
import com.yyusufsefa.expensetracking.data.model.User
import com.yyusufsefa.expensetracking.network.Api
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val api: Api,
    private val dao: ExpenseDao
) {

    suspend fun getCurrencyConvertValue(currencyConvert: String): CurrencyModel {
        return api.getCurrencyValue(currencyConvert, COMPACT, BuildConfig.API_KEY)
    }

    suspend fun insertCurrencyConvert(currencyConvert: CurrencyConvert) {
        dao.insertCurrencyConvert(currencyConvert)
    }

    suspend fun insertExpense(expenseModel: ExpenseModel) {
        dao.insertExpense(expenseModel)
    }

    suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    suspend fun deleteUser() {
        dao.deleteUser()
    }

    suspend fun updateUser(user: User) {
        dao.update(user)
    }

    suspend fun deleteExpenseModel(id: Int) {
        dao.deleteExpenseModel(id)
    }

    fun fetchUser(): LiveData<User> = dao.getUser()

    fun getAllExpenseModel() = dao.getAllExpense()

    fun getCurrencyConvert() = dao.getCurrencyConvert()

    fun getTotalTRY(): Flow<Double?> = dao.getTotalTRY()

    fun getTotalUSD(): Flow<Double?> = dao.getTotalUSD()

    fun getTotalEUR(): Flow<Double?> = dao.getTotalEUR()

    fun getTotalGBP(): Flow<Double?> = dao.getTotalGBP()

    companion object {
        private const val COMPACT = "ultra"
    }
}