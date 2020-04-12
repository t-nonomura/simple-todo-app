package com.treeengineering.simpletodoapp.data.db.dao

import androidx.room.*
import com.treeengineering.simpletodoapp.data.db.entity.ToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    /**
     * ToDo追加
     */
    @Insert
    suspend fun insert(toDoEntity: ToDo)

    /**
     * ToDo更新
     */
    @Update
    suspend fun update(toDoEntity: ToDo)

    /**
     * 全てのToDoリスト取得
     * @return ToDoリストの監視
     */
    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getToDoList(): Flow<List<ToDo>>

    /**
     * 全てのToDoを削除
     */
    @Query("DELETE FROM todo")
    suspend fun deleteAll()

    /**
     * 指定したToDoを削除
     */
    @Delete
    suspend fun delete(todo: ToDo)
}
