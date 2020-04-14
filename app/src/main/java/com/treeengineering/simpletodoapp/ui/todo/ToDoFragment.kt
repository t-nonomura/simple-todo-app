package com.treeengineering.simpletodoapp.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.treeengineering.simpletodoapp.R
import com.treeengineering.simpletodoapp.databinding.FragmentTodoBinding
import kotlinx.android.synthetic.main.fragment_todo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToDoFragment : Fragment() {
    private val toDoViewModel: ToDoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentTodoBinding.bind(view)
        binding.addButtonVisibility = View.INVISIBLE
        setupToDoPager(binding)
        // ToDo入力欄のリスナ設定
        edit_text_todo.doOnTextChanged { text, _, _, _ ->
            toDoViewModel.todoEditText = text.toString()
            binding.addButtonEnabled = text.toString().isNotEmpty()
            binding.addButtonVisibility =
                if (text.toString().isNotEmpty()) View.VISIBLE else View.INVISIBLE
        }
        // ToDo追加ボタンのリスナ設定
        image_add_todo.setOnClickListener {
            toDoViewModel.clickedAddToDoButton()
            binding.todoText = ""
        }
    }

    /**
     * ViewPager、LayoutTabの設定
     */
    private fun setupToDoPager(binding: FragmentTodoBinding) {
        val tabLayoutMediator = TabLayoutMediator(
            binding.layoutTab,
            binding.viewPager
        ) { tab, position ->
            tab.text = ToDoPage.pages[position].title
        }

        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = ToDoPage.pages.size

            override fun createFragment(position: Int): Fragment {
                return ToDoListFragment.newInstance(
                    ToDoListFragmentArgs(position)
                )
            }
        }

        tabLayoutMediator.attach()
    }


}
