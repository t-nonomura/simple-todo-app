package com.treeengineering.simpletodoapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.treeengineering.simpletodoapp.data.db.entity.ToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    /**
     * レコード追加
     * @return rowId
     */
    @Insert
    suspend fun insert(toDoEntity: ToDoEntity): Long

    /**
     * テーブル内の全レコード取得
     * @return ToDoEntityリストの監視
     */
    @Query("SELECT * FROM todo")
    fun getToDoList(): Flow<List<ToDoEntity>>

    /**
     * レコード削除
     */
    @Query("DELETE FROM todo")
    suspend fun deleteAll()
}
