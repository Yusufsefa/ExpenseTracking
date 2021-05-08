package com.yyusufsefa.expensetracking.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yyusufsefa.expensetracking.data.model.CurrencyConvert
import com.yyusufsefa.expensetracking.data.model.ExpenseModel
import com.yyusufsefa.expensetracking.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user")
    fun getUser(): LiveData<User>

    @Update
    suspend fun update(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteUser()

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertExpense(expenseModel: ExpenseModel)

    @Query("SELECT * FROM expense_model ORDER BY _id DESC")
    fun getAllExpense(): Flow<List<ExpenseModel>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyConvert(currencyConvert: CurrencyConvert)

    @Query("SELECT * FROM currency_convert")
    fun getCurrencyConvert(): Flow<CurrencyConvert?>

    @Query("DELETE FROM expense_model WHERE _id =:id")
    suspend fun deleteExpenseModel(id: Int)

    @Query("SELECT SUM(tryPrice) FROM expense_model")
    fun getTotalTRY(): Flow<Double?>

    @Query("SELECT SUM(usdPrice) FROM expense_model")
    fun getTotalUSD(): Flow<Double?>

    @Query("SELECT SUM(eurPrice) FROM expense_model")
    fun getTotalEUR(): Flow<Double?>

    @Query("SELECT SUM(gbpPrice) FROM expense_model")
    fun getTotalGBP(): Flow<Double?>

}