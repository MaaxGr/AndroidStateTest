package de.maaxgr.statetest1

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class TestApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        lateinit var singleton: MySingleton
    }


    override fun onCreate() {
        super.onCreate()
        context = this
        singleton = MySingleton()
    }

}