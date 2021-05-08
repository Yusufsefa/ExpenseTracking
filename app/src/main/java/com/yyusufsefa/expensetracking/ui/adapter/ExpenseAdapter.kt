package com.yyusufsefa.expensetracking.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.expensetracking.data.model.ExpenseModel
import com.yyusufsefa.expensetracking.data.viewState.ExpenseItemViewState
import com.yyusufsefa.expensetracking.databinding.ItemExpenseBinding

class ExpenseAdapter : ListAdapter<ExpenseModel, ExpenseAdapter.ExpenseViewHolder>(diffCallBack) {

    private var onItemClickListener: ((ExpenseModel) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: ((ExpenseModel) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseViewHolder = ExpenseViewHolder(
        ItemExpenseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ).apply {
        this.itemView.setOnClickListener { onItemClickListener?.invoke(getItem(adapterPosition)) }
    }

    override fun onBindViewHolder(holder: ExpenseAdapter.ExpenseViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ExpenseViewHolder(private val expenseBinding: ItemExpenseBinding) :
        RecyclerView.ViewHolder(expenseBinding.root) {
        fun bind(expenseModel: ExpenseModel) {
            expenseBinding.itemViewState = ExpenseItemViewState(expenseModel)
        }
    }

    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<ExpenseModel>() {
            override fun areItemsTheSame(oldItem: ExpenseModel, newItem: ExpenseModel): Boolean =
                oldItem._id == newItem._id

            override fun areContentsTheSame(oldItem: ExpenseModel, newItem: ExpenseModel): Boolean =
                oldItem == newItem
        }
    }
}