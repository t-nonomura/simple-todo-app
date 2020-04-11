package com.treeengineering.simpletodoapp.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.treeengineering.simpletodoapp.R
import com.treeengineering.simpletodoapp.databinding.FragmentTodoBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToDoFragment : Fragment() {
    private val todoViewModel: ToDoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentTodoBinding.bind(view)
        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.todoRecyclerView.adapter = adapter

        setUpObserve(adapter)
    }

    private fun setUpObserve(adapter: GroupAdapter<GroupieViewHolder>) {
        todoViewModel.todoList.observe(viewLifecycleOwner, Observer { todoList ->
            todoList?.let {
                adapter.update(it.map { todo ->
                    ToDoListItem(todo)
                })
            }
        })

        todoViewModel.completedToDoList.observe(viewLifecycleOwner, Observer { todoList ->
            todoList?.let {
                adapter.update(it.map { todo ->
                    ToDoListItem(todo)
                })
            }
        })

        todoViewModel.notCompletedToDoList.observe(viewLifecycleOwner, Observer { todoList ->
            todoList?.let {
                adapter.update(it.map { todo ->
                    ToDoListItem(todo)
                })
            }
        })
    }
}
