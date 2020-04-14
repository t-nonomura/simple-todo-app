package com.treeengineering.simpletodoapp.ui.todo

sealed class ToDoPage() {
    abstract val title: String

    /**
     * すべてタブ
     */
    object All : ToDoPage() {
        override val title = "All"
    }

    /**
     * 進行中タブ
     */
    object Active : ToDoPage() {
        override val title = "Active"
    }

    /**
     * 完了タブ
     */
    object Completed : ToDoPage() {
        override val title = "Completed"
    }

    companion object {
        val pages = listOf(All, Active, Completed)
    }
}
