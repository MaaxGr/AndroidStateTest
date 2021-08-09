package de.maaxgr.statetest1.prefcache

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.maaxgr.statetest1.TestApplication

class PreferenceCacheImpl : PreferenceCache {

    private val gson: Gson = Gson()

    private val preferences = TestApplication.context.getSharedPreferences(
        "PrefCache", Context.MODE_PRIVATE
    )

    override fun <T> getValue(key: String, default: T): T {
        val prefValue = preferences.getString(key, null)
            ?: return default

        println("Value: $prefValue")

        return gson.fromJson(prefValue, object: TypeToken<T>() {}.type)
    }

    override fun <T> setValue(key: String, value: T) {
        val json = gson.toJson(value)
        preferences.edit().putString(key, json).apply()
    }

    override fun resetCache() {
        preferences.edit().clear().apply()
    }

}
