package com.treeengineering.simpletodoapp

import android.app.Application
import com.treeengineering.simpletodoapp.di.actionCreatorModule
import com.treeengineering.simpletodoapp.di.databaseModule
import com.treeengineering.simpletodoapp.di.repositoryModule
import com.treeengineering.simpletodoapp.di.storeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        setupDI()
    }

    private fun setupDI() {
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    actionCreatorModule,
                    databaseModule,
                    repositoryModule,
                    storeModule
                )
            )
        }
    }
}
