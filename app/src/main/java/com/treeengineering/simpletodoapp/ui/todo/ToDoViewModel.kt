package com.treeengineering.simpletodoapp.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.treeengineering.simpletodoapp.data.db.entity.ToDo
import com.treeengineering.simpletodoapp.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(private val toDoRepository: ToDoRepository) : ViewModel() {

    /**
     * ToDo一覧
     * Roomから取得したFlowをLiveDataに変換したもの
     */
    val todoList = toDoRepository.getToDoList().asLiveData()

    /**
     * ToDo追加時の入力文字列
     */
    var todoEditText = ""

    /**
     * ToDo追加ボタンのタップ処理
     */
    fun clickedAddToDoButton() = viewModelScope.launch(Dispatchers.Main) {
        val toDo = ToDo(
            id = 0,
            title = todoEditText,
            completed = false
        )
        toDoRepository.addToDo(toDo)
    }

    /**
     * チェックボックスをタップしたToDoを更新
     */
    fun checkToDo(toDo: ToDo) = viewModelScope.launch(Dispatchers.Main) {
        toDoRepository.updateToDo(toDo)
    }

    /**
     * 指定したToDoを削除
     */
    fun deleteToDo(toDo: ToDo) = viewModelScope.launch(Dispatchers.Main) {
        toDoRepository.delete(toDo)
    }
}
