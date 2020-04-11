package com.treeengineering.simpletodoapp.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.treeengineering.simpletodoapp.data.repository.ToDoRepository

class ToDoViewModel(private val toDoRepository: ToDoRepository) : ViewModel() {
    /**
     * 全てのToDo一覧
     * Roomから取得したFlowをLiveDataに変換したもの
     */
    val todoList = toDoRepository.getToDoList().asLiveData()

    /**
     * 完了済みのToDo一覧
     * Roomから取得したFlowをLiveDataに変換したもの
     */
    val completedToDoList = toDoRepository.getCompletedToDoList().asLiveData()

    /**
     * 未完了のToDo一覧
     * Roomから取得したFlowをLiveDataに変換したもの
     */
    val notCompletedToDoList = toDoRepository.getNotCompletedToDoList().asLiveData()
}
