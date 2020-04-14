package com.treeengineering.simpletodoapp.data.repository

import com.treeengineering.simpletodoapp.data.db.dao.ToDoDao
import com.treeengineering.simpletodoapp.data.db.entity.ToDo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ToDoRepository {
    /**
     * ToDo追加
     */
    suspend fun addToDo(todo: ToDo)

    /**
     * ToDo更新
     */
    suspend fun updateToDo(todo: ToDo)

    /**
     * 全てのToDoを取得
     */
    fun getToDoList(): Flow<List<ToDo>>

    /**
     * 完了済みのToDoを取得
     */
    fun getCompletedToDoList(): Flow<List<ToDo>>

    /**
     * 進行中のToDoを取得
     */
    fun getActiveToDoList(): Flow<List<ToDo>>

    /**
     * 全てのToDoを削除
     */
    suspend fun deleteAll()

    /**
     * 指定したToDoを削除
     */
    suspend fun delete(todo: ToDo)
}

class ToDoRepositoryImpl(private val toDoDao: ToDoDao) : ToDoRepository {

    override suspend fun addToDo(todo: ToDo) {
        toDoDao.insert(todo)
    }

    override suspend fun updateToDo(todo: ToDo) {
        toDoDao.update(todo)
    }

    override fun getToDoList(): Flow<List<ToDo>> {
        return toDoDao.getToDoList()
    }

    override fun getCompletedToDoList(): Flow<List<ToDo>> {
        return getToDoList().map { list ->
            list.filter { it.completed }
        }
    }

    override fun getActiveToDoList(): Flow<List<ToDo>> {
        return getToDoList().map { list ->
            list.filterNot { it.completed }
        }
    }

    override suspend fun deleteAll() {
        toDoDao.deleteAll()
    }

    override suspend fun delete(todo: ToDo) {
        toDoDao.delete(todo)
    }
}
