package de.maaxgr.statetest1.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import de.maaxgr.statetest1.TestApplication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map

class AndroidPreference : Preference {

    private val sharedPref = TestApplication.context.getSharedPreferences("Test", Context.MODE_PRIVATE)

    fun getStringPref(name: String): String? {
        return sharedPref.getString(name, null)
    }

    fun setStringPref(name: String, value: String) {
        sharedPref.edit().putString(name, value).apply()
    }

}