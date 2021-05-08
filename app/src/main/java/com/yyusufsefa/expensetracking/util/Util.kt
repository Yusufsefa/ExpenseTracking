package com.yyusufsefa.expensetracking.util

import android.content.Context
import android.content.res.Resources
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.yyusufsefa.expensetracking.R
import com.yyusufsefa.expensetracking.data.model.CurrencyType
import com.yyusufsefa.expensetracking.data.model.ExpenseModel
import com.yyusufsefa.expensetracking.data.model.ExpenseType

fun EditText.getAsDouble() = this.text.toString().toDouble()

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.networkShowToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

@BindingAdapter("expenseTypeIcon")
fun ImageView.setExpenseType(expenseType: ExpenseType?) {
    expenseType?.let {
        setImageResource(
            when (expenseType) {
                ExpenseType.RENT -> R.drawable.ic_rent
                ExpenseType.BILL -> R.drawable.ic_bill
                ExpenseType.OTHER -> R.drawable.ic_other
                else -> R.drawable.ic_other
            }
        )
    }
}

@BindingAdapter("currencyConverter")
fun TextView.setCurrencyCovertType(expenseModel: ExpenseModel?) {
    expenseModel?.let {
        text =
            convertCurrencyType(expenseModel, context.resources)
    }
}

fun convertCurrencyType(expenseModel: ExpenseModel, resources: Resources): String =
    when (expenseModel.currencyType) {
        CurrencyType.TRY -> resources.getString(R.string.currency_TL, expenseModel.tryPrice)
        CurrencyType.USD -> resources.getString(R.string.currency_USD, expenseModel.usdPrice)
        CurrencyType.EUR -> resources.getString(R.string.currency_EUR, expenseModel.eurPrice)
        CurrencyType.GBP -> resources.getString(R.string.currency_GBP, expenseModel.gbpPrice)
        else -> ""
    }


fun TextInputEditText.isNotNullOrEmpty(errorString: String): Boolean {
    val textInputLayout = this.parent.parent as TextInputLayout
    textInputLayout.errorIconDrawable = null
    this.doOnTextChanged { _, _, _, _ ->
        textInputLayout.error = null
    }

    return if (this.text.toString().trim().isEmpty()) {
        textInputLayout.error = errorString
        false
    } else {
        true
    }
}

fun validateAndDo(textInputEditText: List<TextInputEditText>, block: () -> Unit) {
    var boolean = false
    for (text in textInputEditText) {
        if (!text.isNotNullOrEmpty("Zorunlu"))
            boolean = true
    }
    if (!boolean)
        block()
}

