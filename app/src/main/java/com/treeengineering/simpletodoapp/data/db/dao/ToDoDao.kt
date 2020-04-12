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
     * 指定したpositionのToDoを取得
     */
    @Query("SELECT * FROM todo WHERE id = (SELECT id FROM todo ORDER BY id DESC LIMIT :position, 1)")
    suspend fun getTodoFromPosition(position: Int): ToDo?

    /**
     * 初回表示用に全てのToDoリスト取得
     * @return ToDoリスト
     */
    @Query("SELECT * FROM todo ORDER BY id DESC")
    suspend fun getFirstToDoList(): List<ToDo>?

    /**
     * 全てのToDoリスト取得
     * @return ToDoリストの監視
     */
    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getToDoList(): Flow<List<ToDo>?>

    /**
     * 完了済みToDoリストの取得
     * @return 完了済みToDoリストの監視
     */
    @Query("SELECT * FROM todo WHERE completed = 1 ORDER BY id DESC")
    fun getCompletedToDoList(): Flow<List<ToDo>?>

    /**
     * 未完了ToDoリストの取得
     * @return 未完了ToDoリストの監視
     */
    @Query("SELECT * FROM todo WHERE completed = 0 ORDER BY id DESC")
    fun getNotCompletedToDoList(): Flow<List<ToDo>?>

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
