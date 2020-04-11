package com.treeengineering.simpletodoapp.ui.todo

import com.treeengineering.simpletodoapp.R
import com.treeengineering.simpletodoapp.data.db.entity.LocalToDo
import com.treeengineering.simpletodoapp.databinding.ItemTodoListBinding
import com.xwray.groupie.databinding.BindableItem

class ToDoListItem(private val todo: LocalToDo) : BindableItem<ItemTodoListBinding>() {
    override fun getLayout() = R.layout.item_todo_list

    override fun bind(viewBinding: ItemTodoListBinding, position: Int) {
        viewBinding.todoTitle.text = todo.title
    }
}