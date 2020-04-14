package com.treeengineering.simpletodoapp.ui.todo

import androidx.collection.LongSparseArray
import androidx.collection.set
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treeengineering.simpletodoapp.data.db.entity.ToDo
import com.treeengineering.simpletodoapp.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ToDoListViewModel(private val toDoRepository: ToDoRepository) : ViewModel() {
    /**
     * ToDo一覧
     * Roomから取得したFlowをLiveDataに変換したもの
     */
    val todoList = MutableLiveData<List<ToDo>>()

    /**
     * スクロール位置の保存
     */
    val scrollPosition = MutableLiveData<Int>()

    private lateinit var todoTab: ToDoPage

    /**
     * Fragmentの開始と共に呼ばれる処理
     */
    fun onCreate(tabIndex: Int) = viewModelScope.launch(Dispatchers.Main) {
        todoTab = ToDoPage.pages[tabIndex]
        when (todoTab) {
            // すべて
            ToDoPage.All -> {
                toDoRepository.getToDoList().collect {
                    val oldList = todoList.value ?: listOf()
                    val newList = it
                    todoList.value = it
                    updateScroll(oldList, newList)
                }
            }
            // 進行中
            ToDoPage.Active -> {
                toDoRepository.getActiveToDoList().collect {
                    val oldList = todoList.value ?: listOf()
                    val newList = it
                    todoList.value = it
                    updateScroll(oldList, newList)
                }
            }
            // 完了
            ToDoPage.Completed -> {
                toDoRepository.getCompletedToDoList().collect {
                    val oldList = todoList.value ?: listOf()
                    val newList = it
                    todoList.value = it
                    updateScroll(oldList, newList)
                }
            }
        }
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

    /**
     * スクロール位置の更新
     */
    private fun updateScroll(oldList: List<ToDo>, newList: List<ToDo>) {
        if (oldList.isEmpty()) {
            // 初回
            return
        }
        // 更新
        val map = LongSparseArray<ToDo>()
        oldList.map { map[it.id] = it }
        // 更新された項目を探す
        var scrollIndex = -1
        newList.mapIndexed { index, item ->
            val oldItem = map.get(item.id)
            if (oldItem != item) {
                // 古いものと違う場合はその場所にスクロール
                scrollIndex = index
            }
        }
        if (scrollIndex >= 0) {
            scrollPosition.value = scrollIndex
        }
    }
}
