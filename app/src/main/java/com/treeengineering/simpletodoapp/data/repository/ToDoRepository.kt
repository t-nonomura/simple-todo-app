package com.treeengineering.simpletodoapp.data.repository

import com.treeengineering.simpletodoapp.data.db.SimpleToDoDatabase
import com.treeengineering.simpletodoapp.data.db.entity.ToDoEntity
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    /**
     * 全てのToDoを取得
     */
    fun getToDoList(): Flow<List<ToDoEntity>>

    /**
     * 完了済みのToDoを取得
     */
    fun getCompletedToDoList(): Flow<List<ToDoEntity>>

    /**
     * 未完了のToDoを取得
     */
    fun getNotCompletedToDoList(): Flow<List<ToDoEntity>>
}

class ToDoRepositoryImpl(db: SimpleToDoDatabase) : ToDoRepository {
    private val toDoDao = db.toDoDao()

    override fun getToDoList(): Flow<List<ToDoEntity>> {
        return toDoDao.getToDoList()
    }

    override fun getCompletedToDoList(): Flow<List<ToDoEntity>> {
        return toDoDao.getCompletedToDoList()
    }

    override fun getNotCompletedToDoList(): Flow<List<ToDoEntity>> {
        return toDoDao.getNotCompletedToDoList()
    }
}
