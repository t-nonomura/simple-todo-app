package com.treeengineering.simpletodoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.treeengineering.simpletodoapp.data.db.dao.ToDoDao
import com.treeengineering.simpletodoapp.data.db.entity.ToDoEntity

@Database(
    entities = [ToDoEntity::class],
    version = 1
)
@TypeConverters(SimpleToDoDatabaseConverter::class)
abstract class SimpleToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}
