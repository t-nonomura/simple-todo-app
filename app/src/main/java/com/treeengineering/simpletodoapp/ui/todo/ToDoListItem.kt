package com.treeengineering.simpletodoapp.ui.todo

import com.treeengineering.simpletodoapp.R
import com.treeengineering.simpletodoapp.data.db.entity.ToDo
import com.treeengineering.simpletodoapp.databinding.ItemTodoListBinding
import com.xwray.groupie.databinding.BindableItem

class ToDoListItem(
    private val todo: ToDo,
    private val clickListener: ClickListener
) : BindableItem<ItemTodoListBinding>() {
    interface ClickListener {
        fun onClickItem(position: Int)
        fun onClickCheckbox(position: Int)
        fun onClickDelete(position: Int)
    }

    override fun getLayout() = R.layout.item_todo_list

    override fun bind(viewBinding: ItemTodoListBinding, position: Int) {
        viewBinding.textTodoTitle.text = todo.title
        viewBinding.cardViewTodo.setOnClickListener { clickListener.onClickItem(position) }
        viewBinding.checkboxTodo.setOnClickListener { clickListener.onClickCheckbox(position) }
        viewBinding.imageDeleteTodo.setOnClickListener { clickListener.onClickDelete(position) }
    }
}
