package de.maaxgr.statetest1

import android.app.Application

class TestApplication : Application() {

    companion object {
        var globalState: String? = null
    }

    override fun onCreate() {
        super.onCreate()
    }

}