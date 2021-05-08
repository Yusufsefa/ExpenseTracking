package com.yyusufsefa.expensetracking.ui.addEditUser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yyusufsefa.expensetracking.R
import com.yyusufsefa.expensetracking.common.BaseFragment
import com.yyusufsefa.expensetracking.data.model.Gender
import com.yyusufsefa.expensetracking.data.model.User
import com.yyusufsefa.expensetracking.databinding.FragmentAddEditUserBinding
import com.yyusufsefa.expensetracking.util.showToast
import com.yyusufsefa.expensetracking.util.validateAndDo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditUserFragment :
    BaseFragment<FragmentAddEditUserBinding>(R.layout.fragment_add_edit_user) {

    private val viewModelAdd: AddEditUserViewModel by viewModels()

    private val user by lazy { User() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            onSaveClick()
        }

        if (viewModelAdd.isLogin)
            initUI()
    }

    private fun getUserFromUI(): User {
        with(binding) {
            user.name = txtEditName.text.toString()
            when {
                radioButtonMale.isChecked -> user.gender = Gender.MALE
                radioButtonFemale.isChecked -> user.gender = Gender.FEMALE
                radioButtonIdle.isChecked -> user.gender = Gender.IDLE
            }
        }
        return user
    }

    private fun initUI() {
        viewModelAdd.user.observe(viewLifecycleOwner, { user ->
            with(binding) {
                txtEditName.setText(user.name)
                when (user.gender) {
                    Gender.MALE -> radioButtonMale.isChecked = true
                    Gender.FEMALE -> radioButtonFemale.isChecked = true
                    Gender.IDLE -> radioButtonIdle.isChecked = true
                }
            }
        })
    }

    private fun onSaveClick() {
        validateAndDo(
            listOf(
                binding.txtEditName
            )
        ) {
            validateInputAndRun {
                if (viewModelAdd.isLogin) {
                    viewModelAdd.updateUser(getUserFromUI())
                } else {
                    viewModelAdd.insertUser(getUserFromUI())
                    viewModelAdd.setPrefLogin(true)
                }
                findNavController().navigate(R.id.action_editUserFragment_to_homeFragment)
            }
        }
    }

    private fun validateInputAndRun(block: () -> Unit) {
        binding.let {
            when {
                it.txtEditName.text.isNullOrEmpty() -> requireContext().showToast(getString(R.string.add_edit_name_error_msg))
                (it.radioGroupGender.checkedRadioButtonId <= 0) -> requireContext().showToast(
                    getString(R.string.radio_button_gender_type_error)
                )
                else -> block()
            }
        }
    }
}