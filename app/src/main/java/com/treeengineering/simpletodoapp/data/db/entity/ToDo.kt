package com.treeengineering.simpletodoapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val completed: Boolean
)
