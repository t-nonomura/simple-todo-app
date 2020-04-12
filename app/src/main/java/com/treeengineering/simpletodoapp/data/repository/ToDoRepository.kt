package com.treeengineering.simpletodoapp.data.repository

import com.treeengineering.simpletodoapp.data.db.dao.ToDoDao
import com.treeengineering.simpletodoapp.data.db.entity.ToDo
import kotlinx.coroutines.flow.Flow

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
     * 指定したpositionのToDoを取得
     */
    suspend fun getToDoFromPosition(position: Int): ToDo?

    /**
     * 全てのToDoを取得(初回表示用に直接リストを取得)
     */
    suspend fun getFirstToDoList(): List<ToDo>?

    /**
     * 全てのToDoを取得
     */
    fun getToDoList(): Flow<List<ToDo>?>

    /**
     * 完了済みのToDoを取得
     */
    fun getCompletedToDoList(): Flow<List<ToDo>?>

    /**
     * 未完了のToDoを取得
     */
    fun getNotCompletedToDoList(): Flow<List<ToDo>?>

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

    override suspend fun getToDoFromPosition(position: Int): ToDo? {
        return toDoDao.getTodoFromPosition(position)
    }

    override suspend fun getFirstToDoList(): List<ToDo>? {
        return toDoDao.getFirstToDoList()
    }

    override fun getToDoList(): Flow<List<ToDo>?> {
        return toDoDao.getToDoList()
    }

    override fun getCompletedToDoList(): Flow<List<ToDo>?> {
        return toDoDao.getCompletedToDoList()
    }

    override fun getNotCompletedToDoList(): Flow<List<ToDo>?> {
        return toDoDao.getNotCompletedToDoList()
    }

    override suspend fun deleteAll() {
        toDoDao.deleteAll()
    }

    override suspend fun delete(todo: ToDo) {
        toDoDao.delete(todo)
    }
}
