package com.treeengineering.simpletodoapp.di

import com.treeengineering.simpletodoapp.data.db.SimpleToDoDatabase
import com.treeengineering.simpletodoapp.data.db.SimpleToDoDatabaseFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    // Databaseインスタンス
    single { SimpleToDoDatabaseFactory.create(androidContext()) }
    // Dao
    factory { get<SimpleToDoDatabase>().toDoDao() }
}
