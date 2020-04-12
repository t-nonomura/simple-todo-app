package com.treeengineering.simpletodoapp.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import com.treeengineering.simpletodoapp.R
import com.treeengineering.simpletodoapp.data.db.entity.ToDo
import com.treeengineering.simpletodoapp.databinding.FragmentTodoBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_todo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToDoFragment : Fragment() {
    private val toDoViewModel: ToDoViewModel by viewModel()
    private lateinit var adapter: GroupAdapter<GroupieViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = GroupAdapter()
        val binding = FragmentTodoBinding.bind(view)
        binding.recyclerViewTodo.adapter = adapter
        val itemAnimator = binding.recyclerViewTodo.itemAnimator
        if (itemAnimator is SimpleItemAnimator) {
            itemAnimator.supportsChangeAnimations = false
        }

        // LiveDataの監視
        setUpObserve()
        // ToDo入力欄の設定
        edit_text_todo.doOnTextChanged { text, _, _, _ ->
            toDoViewModel.todoEditText = text.toString()
            binding.addButtonEnabled = text.toString().isNotEmpty()
        }
        // ToDo追加ボタン
        image_add_todo.setOnClickListener {
            toDoViewModel.clickedAddToDoButton()
            binding.todoText = ""
        }
    }

    private fun setUpObserve() {
        val clickListener = object : ToDoListItem.ClickListener {
            override fun onClickItem(toDo: ToDo) {
                // TODO 機能拡張
            }

            override fun onClickCheckbox(toDo: ToDo) {
                toDoViewModel.checkToDo(toDo)
            }

            override fun onClickDelete(toDo: ToDo) {
                toDoViewModel.deleteToDo(toDo)
            }
        }

        toDoViewModel.todoList.observe(viewLifecycleOwner, Observer { todoList ->
            todoList?.let {
                adapter.update(it.map { todo ->
                    ToDoListItem(todo, clickListener)
                })
            }
        })
    }
}
