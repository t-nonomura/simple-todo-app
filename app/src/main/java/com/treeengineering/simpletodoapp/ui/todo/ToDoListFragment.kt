package com.treeengineering.simpletodoapp.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import com.treeengineering.simpletodoapp.R
import com.treeengineering.simpletodoapp.data.db.entity.ToDo
import com.treeengineering.simpletodoapp.databinding.FragmentTodoListBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_todo_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToDoListFragment : Fragment() {
    private val toDoListViewModel: ToDoListViewModel by viewModel()
    private val args: ToDoListFragmentArgs by lazy {
        ToDoListFragmentArgs.fromBundle(arguments ?: Bundle())
    }

    private lateinit var adapter: GroupAdapter<GroupieViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GroupAdapter()
        val binding = FragmentTodoListBinding.bind(view)
        binding.recyclerViewList.adapter = adapter
        val itemAnimator = binding.recyclerViewList.itemAnimator
        if (itemAnimator is SimpleItemAnimator) {
            itemAnimator.supportsChangeAnimations = false
        }

        setUpObserve()
    }

    /**
     * LiveDataの監視設定
     */
    private fun setUpObserve() {
        val clickListener = object : ToDoListItem.ClickListener {
            override fun onClickItem(toDo: ToDo) {
                // TODO 機能拡張用
            }

            override fun onClickCheckbox(toDo: ToDo) {
                toDoListViewModel.checkToDo(toDo)
            }

            override fun onClickDelete(toDo: ToDo) {
                toDoListViewModel.deleteToDo(toDo)
            }
        }

        // リストの更新を監視
        toDoListViewModel.todoList.observe(viewLifecycleOwner, Observer { todoList ->
            todoList?.let {
                adapter.update(it.map { todo ->
                    ToDoListItem(todo, clickListener)
                })
            }
        })

        // スクロール位置の更新を監視
        toDoListViewModel.scrollPosition.observe(viewLifecycleOwner, Observer { position ->
            position?.let {
                recycler_view_list.scrollToPosition(it)
            }
        })

        // 初期化処理
        toDoListViewModel.onCreate(args.tabIndex)
    }

    companion object {
        fun newInstance(args: ToDoListFragmentArgs): ToDoListFragment {
            return ToDoListFragment().apply {
                this.arguments = args.toBundle()
            }
        }
    }
}
