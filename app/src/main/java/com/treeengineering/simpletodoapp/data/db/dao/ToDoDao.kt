package com.treeengineering.simpletodoapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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
     * 初回表示用に全てのToDoリスト取得
     * @return ToDoリスト
     */
    @Query("SELECT * FROM todo")
    suspend fun getFirstToDoList(): List<ToDo>?

    /**
     * 全てのToDoリスト取得
     * @return ToDoリストの監視
     */
    @Query("SELECT * FROM todo")
    fun getToDoList(): Flow<List<ToDo>?>

    /**
     * 完了済みToDoリストの取得
     * @return 完了済みToDoリストの監視
     */
    @Query("SELECT * FROM todo WHERE completed = 1")
    fun getCompletedToDoList(): Flow<List<ToDo>?>

    /**
     * 未完了ToDoリストの取得
     * @return 未完了ToDoリストの監視
     */
    @Query("SELECT * FROM todo WHERE completed = 0")
    fun getNotCompletedToDoList(): Flow<List<ToDo>?>

    /**
     * 全てのToDo削除
     */
    @Query("DELETE FROM todo")
    suspend fun deleteAll()
}
