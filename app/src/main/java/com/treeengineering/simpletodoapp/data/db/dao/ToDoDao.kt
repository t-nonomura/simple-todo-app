package com.treeengineering.simpletodoapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.treeengineering.simpletodoapp.data.db.entity.ToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    /**
     * ToDo追加
     * @return rowId
     */
    @Insert
    suspend fun insert(toDoEntity: ToDoEntity): Long

    /**
     * 全てのToDoリスト取得
     * @return ToDoリストの監視
     */
    @Query("SELECT * FROM todo")
    fun getToDoList(): Flow<List<ToDoEntity>>

    /**
     * 完了済みToDoリストの取得
     * @return 完了済みToDoリストの監視
     */
    @Query("SELECT * FROM todo WHERE completed = 1")
    fun getCompletedToDoList(): Flow<List<ToDoEntity>>

    /**
     * 未完了ToDoリストの取得
     * @return 未完了ToDoリストの監視
     */
    @Query("SELECT * FROM todo WHERE completed = 0")
    fun getNotCompletedToDoList(): Flow<List<ToDoEntity>>

    /**
     * 全てのToDo削除
     */
    @Query("DELETE FROM todo")
    suspend fun deleteAll()
}
