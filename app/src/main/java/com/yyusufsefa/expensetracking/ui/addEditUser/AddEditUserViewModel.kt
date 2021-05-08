package com.yyusufsefa.expensetracking.ui.addEditUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyusufsefa.expensetracking.data.model.User
import com.yyusufsefa.expensetracking.data.preferences.MyPreferences
import com.yyusufsefa.expensetracking.data.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditUserViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val preferences: MyPreferences
) : ViewModel() {
    val user: LiveData<User> = currencyRepository.fetchUser()

    val isLogin = preferences.isLogin()

    fun insertUser(user: User) {
        viewModelScope.launch {
            currencyRepository.insertUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            currencyRepository.updateUser(user)
        }
    }

    fun setPrefLogin(isLogin: Boolean) {
        preferences.setLogined(isLogin)
    }
}