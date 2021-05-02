package com.yyusufsefa.expensetracking.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<WD : ViewDataBinding>(@LayoutRes private val layoutRes: Int) :
    Fragment() {

    internal lateinit var binding: WD

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<WD>(
        inflater,
        layoutRes,
        container,
        false
    ).also {
        binding = it
    }.root
}