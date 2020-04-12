package com.treeengineering.simpletodoapp.ui.todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.treeengineering.simpletodoapp.data.db.entity.ToDo
import com.treeengineering.simpletodoapp.data.repository.ToDoRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class ToDoViewModel(private val toDoRepository: ToDoRepository) : ViewModel() {
    /**
     * 全てのToDo一覧
     * 初回取得用
     */
    val firstToDoList = MutableLiveData<List<ToDo>>()

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

    /**
     * ToDo追加時の入力文字列
     */
    var todoEditText = ""

    /**
     * ToDo追加ボタンのタップ処理
     */
    fun clickedAddToDoButton() = GlobalScope.launch {
        val toDo = ToDo(
            id = 0,
            title = todoEditText,
            completed = false,
            createdDate = Date()
        )
        toDoRepository.addToDo(toDo)
    }

    /**
     * 初回表示用にToDo一覧を取得
     */
    fun getFirstTodoList() = GlobalScope.launch {
        toDoRepository.getFirstToDoList()
    }

    /**
     * 指定したpositionのToDoを削除
     */
    fun deleteToDo(position: Int) = GlobalScope.launch {
        val targetToDo = toDoRepository.getToDoFromPosition(position)
        targetToDo?.let {
            toDoRepository.delete(it)
        }
    }
}
