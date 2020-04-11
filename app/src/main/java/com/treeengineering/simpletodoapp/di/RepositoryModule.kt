package com.treeengineering.simpletodoapp.di

import com.treeengineering.simpletodoapp.data.repository.ToDoRepository
import com.treeengineering.simpletodoapp.data.repository.ToDoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory { ToDoRepositoryImpl() as ToDoRepository }
}
