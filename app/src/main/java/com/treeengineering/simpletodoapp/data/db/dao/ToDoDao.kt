package com.treeengineering.simpletodoapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.treeengineering.simpletodoapp.data.db.entity.LocalToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    /**
     * ToDo追加
     */
    @Insert
    suspend fun insert(toDoEntity: LocalToDo)

    /**
     * ToDo更新
     */
    @Update
    suspend fun update(toDoEntity: LocalToDo)

    /**
     * 全てのToDoリスト取得
     * @return ToDoリストの監視
     */
    @Query("SELECT * FROM todo")
    fun getToDoList(): Flow<List<LocalToDo>>

    /**
     * 完了済みToDoリストの取得
     * @return 完了済みToDoリストの監視
     */
    @Query("SELECT * FROM todo WHERE completed = 1")
    fun getCompletedToDoList(): Flow<List<LocalToDo>>

    /**
     * 未完了ToDoリストの取得
     * @return 未完了ToDoリストの監視
     */
    @Query("SELECT * FROM todo WHERE completed = 0")
    fun getNotCompletedToDoList(): Flow<List<LocalToDo>>

    /**
     * 全てのToDo削除
     */
    @Query("DELETE FROM todo")
    suspend fun deleteAll()
}
