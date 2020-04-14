package com.treeengineering.simpletodoapp.ui.todo

import android.os.Bundle
import androidx.navigation.NavArgs

data class ToDoListFragmentArgs(
    val tabIndex: Int
) : NavArgs {
    fun toBundle(): Bundle {
        val result = Bundle()
        result.putInt("tabIndex", this.tabIndex)
        return result
    }

    companion object {
        fun fromBundle(bundle: Bundle): ToDoListFragmentArgs {
            bundle.classLoader = ToDoListFragmentArgs::class.java.classLoader
            val tabIndex = bundle.getInt("tabIndex")
            return ToDoListFragmentArgs(tabIndex)
        }
    }
}
