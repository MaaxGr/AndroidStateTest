package de.maaxgr.statetest1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import de.maaxgr.statetest1.prefcache.PreferenceCacheImpl

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PreferenceCacheImpl().resetCache()

        val btn = findViewById<Button>(R.id.btn)

        TestApplication.singleton.globalState = "Hello World 1"
        TestApplication.singleton.globalState2 = "Hello World 2"
        TestApplication.singleton.globalState3 = "Hello World 3"

        btn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }
}