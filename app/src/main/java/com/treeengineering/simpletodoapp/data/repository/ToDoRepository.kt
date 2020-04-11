package com.treeengineering.simpletodoapp.data.repository

import com.treeengineering.simpletodoapp.data.db.dao.ToDoDao
import com.treeengineering.simpletodoapp.data.db.entity.LocalToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    /**
     * ToDo追加
     */
    suspend fun addToDo(todo: LocalToDo)

    /**
     * ToDo更新
     */
    suspend fun updateToDo(todo: LocalToDo)

    /**
     * 全てのToDoを取得
     */
    fun getToDoList(): Flow<List<LocalToDo>>

    /**
     * 完了済みのToDoを取得
     */
    fun getCompletedToDoList(): Flow<List<LocalToDo>>

    /**
     * 未完了のToDoを取得
     */
    fun getNotCompletedToDoList(): Flow<List<LocalToDo>>
}

class ToDoRepositoryImpl(private val toDoDao: ToDoDao) : ToDoRepository {

    override suspend fun addToDo(todo: LocalToDo) {
        toDoDao.insert(todo)
    }

    override suspend fun updateToDo(todo: LocalToDo) {
        toDoDao.update(todo)
    }

    override fun getToDoList(): Flow<List<LocalToDo>> {
        return toDoDao.getToDoList()
    }

    override fun getCompletedToDoList(): Flow<List<LocalToDo>> {
        return toDoDao.getCompletedToDoList()
    }

    override fun getNotCompletedToDoList(): Flow<List<LocalToDo>> {
        return toDoDao.getNotCompletedToDoList()
    }
}
