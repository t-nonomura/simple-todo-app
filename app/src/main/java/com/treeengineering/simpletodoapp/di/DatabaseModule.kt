package com.treeengineering.simpletodoapp.di

import com.treeengineering.simpletodoapp.data.db.SimpleToDoDatabaseFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { SimpleToDoDatabaseFactory.create(androidContext()) }
}
