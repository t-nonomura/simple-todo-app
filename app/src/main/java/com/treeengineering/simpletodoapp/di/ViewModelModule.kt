package com.treeengineering.simpletodoapp.di

import com.treeengineering.simpletodoapp.ui.todo.ToDoListViewModel
import com.treeengineering.simpletodoapp.ui.todo.ToDoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ToDoViewModel(get()) }
    viewModel { ToDoListViewModel(get()) }
}
