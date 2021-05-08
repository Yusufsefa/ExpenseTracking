package com.yyusufsefa.expensetracking

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.yyusufsefa.expensetracking.data.local.ExpenseDao
import com.yyusufsefa.expensetracking.data.local.ExpenseDatabase
import com.yyusufsefa.expensetracking.data.model.Gender
import com.yyusufsefa.expensetracking.data.model.User
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class DatabaseTest {

    @get:Rule
    var hilRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var dataBase: ExpenseDatabase
    private lateinit var dao: ExpenseDao

    @Before
    fun setup() {
        hilRule.inject()
    }

    @After
    fun tearDown() {
        dataBase.close()
    }

    @Test
    fun instertUser() = runBlocking {
        val user = User(
            name = "yusuf",
            gender = Gender.MALE,
            totalAmount = 1258.078
        )
        dao.insertUser(user)
        val fetchUser = dao.getUser()
    }
}