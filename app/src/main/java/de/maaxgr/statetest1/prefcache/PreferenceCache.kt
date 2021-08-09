package de.maaxgr.statetest1.prefcache

interface PreferenceCache {
    fun resetCache()
    fun <T> setValue(key: String, value: T)
    fun <T> getValue(key: String, default: T): T
}
