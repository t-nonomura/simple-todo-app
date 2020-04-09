package com.treeengineering.simpletodoapp.data.db

import androidx.room.TypeConverter
import java.util.*

class SimpleToDoDatabaseConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        if (value == null) return null
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
