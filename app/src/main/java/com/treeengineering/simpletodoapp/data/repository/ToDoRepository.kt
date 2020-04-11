package com.treeengineering.simpletodoapp.data.repository

import com.treeengineering.simpletodoapp.data.db.entity.ToDoEntity
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    fun getToDoList(): Flow<List<ToDoEntity>>
}

class ToDoRepositoryImpl() : ToDoRepository {
    override fun getToDoList(): Flow<List<ToDoEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
