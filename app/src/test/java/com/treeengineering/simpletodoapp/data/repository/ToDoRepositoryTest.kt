package com.treeengineering.simpletodoapp.data.repository

import com.treeengineering.simpletodoapp.data.db.dao.ToDoDao
import com.treeengineering.simpletodoapp.data.db.entity.ToDo
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ToDoRepositoryTest {
    @MockK(relaxed = true)
    lateinit var toDoDao: ToDoDao

    private lateinit var repository: ToDoRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = ToDoRepositoryImpl(toDoDao)
    }

    @Test
    fun addToDo() = runBlocking {
        val todo = mockk<ToDo>()
        repository.addToDo(todo)
        coVerifySequence {
            toDoDao.insert(todo)
        }
    }

    @Test
    fun updateToDo() = runBlocking {
        val todo = mockk<ToDo>()
        repository.updateToDo(todo)
        coVerifySequence {
            toDoDao.update(todo)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getToDoList() = runBlocking {
        val mockList = mockk<List<ToDo>>()
        every {
            toDoDao.getToDoList()
        } returns flow { emit(mockList) }
        repository.getToDoList().take(1).collect {
            it shouldBe mockList
        }
        coVerifySequence {
            toDoDao.getToDoList()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getCompletedToDoList() = runBlocking {
        val mockList = mockk<List<ToDo>>(relaxed = true)
        every {
            toDoDao.getToDoList().map { list ->
                list.filter { it.completed }
            }
        } returns flow { emit(mockList) }
        repository.getCompletedToDoList().take(1).collect {
            it shouldBe mockList
        }
        coVerifySequence {
            toDoDao.getToDoList()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getNotCompletedToDoList() = runBlocking {
        val mockList = mockk<List<ToDo>>(relaxed = true)
        every {
            toDoDao.getToDoList().map { list ->
                list.filterNot { it.completed }
            }
        } returns flow { emit(mockList) }
        repository.getNotCompletedToDoList().take(1).collect {
            it shouldBe mockList
        }
        coVerifySequence {
            toDoDao.getToDoList()
        }
    }

    @Test
    fun deleteAll() = runBlocking {
        repository.deleteAll()
        coVerifySequence {
            toDoDao.deleteAll()
        }
    }

    @Test
    fun delete() = runBlocking {
        val toDo = mockk<ToDo>()
        repository.delete(toDo)
        coVerifySequence {
            toDoDao.delete(toDo)
        }
    }

}
