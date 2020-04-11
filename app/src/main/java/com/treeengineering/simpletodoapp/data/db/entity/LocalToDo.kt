package com.treeengineering.simpletodoapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todo")
data class LocalToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val completed: Boolean,
    val createdDate: Date
)
