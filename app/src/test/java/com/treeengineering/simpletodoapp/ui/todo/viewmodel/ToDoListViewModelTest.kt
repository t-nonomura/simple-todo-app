package com.treeengineering.simpletodoapp.ui.todo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.treeengineering.simpletodoapp.data.db.entity.ToDo
import com.treeengineering.simpletodoapp.data.repository.ToDoRepository
import com.treeengineering.simpletodoapp.ui.todo.ToDoListViewModel
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ToDoListViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var repository: ToDoRepository

    private lateinit var viewModel: ToDoListViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = ToDoListViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun onCreateAll() = runBlocking {
        val mock = mockk<List<ToDo>>()
        every {
            repository.getToDoList()
        } returns flow { emit(mock) }
        viewModel.onCreate(0)
        coVerifySequence {
            repository.getToDoList()
        }
        viewModel.todoList.value shouldBe mock
    }

    @Test
    fun onCreateActive() = runBlocking {
        val mock = mockk<List<ToDo>>()
        every {
            repository.getActiveToDoList()
        } returns flow { emit(mock) }
        viewModel.onCreate(1)
        coVerifySequence {
            repository.getActiveToDoList()
        }
        viewModel.todoList.value shouldBe mock
    }

    @Test
    fun onCreateCompleted() = runBlocking {
        val mock = mockk<List<ToDo>>()
        every {
            repository.getCompletedToDoList()
        } returns flow { emit(mock) }
        viewModel.onCreate(2)
        coVerifySequence {
            repository.getCompletedToDoList()
        }
        viewModel.todoList.value shouldBe mock
    }

    @Test
    fun checkToDo() = runBlocking {
        val todo = mockk<ToDo>(relaxed = true)
        viewModel.checkToDo(todo)
        coVerifySequence {
            repository.updateToDo(todo)
        }
    }

    @Test
    fun deleteToDo() = runBlocking {
        val todo = mockk<ToDo>(relaxed = true)
        viewModel.deleteToDo(todo)
        coVerifySequence {
            repository.delete(todo)
        }
    }
}
