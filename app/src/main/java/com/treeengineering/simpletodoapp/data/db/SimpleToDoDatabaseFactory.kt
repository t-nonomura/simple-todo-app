package com.treeengineering.simpletodoapp.data.db

import android.content.Context
import androidx.room.Room

object SimpleToDoDatabaseFactory {
    fun create(context: Context): SimpleToDoDatabase {
        return Room.databaseBuilder(
            context,
            SimpleToDoDatabase::class.java,
            "todo.db"
        ).build()
    }
}
