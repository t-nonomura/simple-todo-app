package com.treeengineering.simpletodoapp.ui.todo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.treeengineering.simpletodoapp.data.db.entity.ToDo
import com.treeengineering.simpletodoapp.data.repository.ToDoRepository
import com.treeengineering.simpletodoapp.ui.todo.ToDoViewModel
import io.mockk.MockKAnnotations
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ToDoViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var repository: ToDoRepository

    private lateinit var viewModel: ToDoViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = ToDoViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun clickedAddToButton() = runBlocking {
        viewModel.todoEditText = "mock"
        viewModel.clickedAddToDoButton()
        coVerifySequence {
            repository.addToDo(ToDo(0, "mock", false))
        }
    }

}
