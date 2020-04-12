package com.treeengineering.simpletodoapp.ui.todo

import android.graphics.Paint
import com.treeengineering.simpletodoapp.R
import com.treeengineering.simpletodoapp.data.db.entity.ToDo
import com.treeengineering.simpletodoapp.databinding.ItemTodoListBinding
import com.xwray.groupie.databinding.BindableItem

class ToDoListItem(
    private val toDo: ToDo,
    private val clickListener: ClickListener
) : BindableItem<ItemTodoListBinding>(toDo.id) {
    interface ClickListener {
        fun onClickItem(toDo: ToDo)
        fun onClickCheckbox(toDo: ToDo)
        fun onClickDelete(toDo: ToDo)
    }

    override fun getLayout() = R.layout.item_todo_list

    override fun bind(viewBinding: ItemTodoListBinding, position: Int) {
        // 完了済みのToDoには取り消し線を付ける
        val paint = viewBinding.textTodoTitle.paint
        if (toDo.completed) {
            paint.flags = viewBinding.textTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            paint.isAntiAlias = true
        } else {
            paint.flags = viewBinding.textTodoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        viewBinding.textTodoTitle.text = toDo.title
        viewBinding.checkboxTodo.isChecked = toDo.completed
        viewBinding.cardViewTodo.setOnClickListener {
            clickListener.onClickItem(toDo)
        }
        viewBinding.checkboxTodo.setOnCheckedChangeListener { _, isChecked ->
            clickListener.onClickCheckbox(toDo.copy(completed = isChecked))
        }
        viewBinding.imageDeleteTodo.setOnClickListener {
            clickListener.onClickDelete(toDo)
        }
    }
}

