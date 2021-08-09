package de.maaxgr.statetest1

import de.maaxgr.statetest1.prefcache.PreferenceCacheImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty


class PrefCacheDelegate<T: Any>(private val argumentKey: String, private val defaultValue: T) {

    private val preferenceCache: PreferenceCacheImpl = PreferenceCacheImpl()
    private val classScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private lateinit var state: T

    private var initial = false
    private var keyName = ""

    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        if (!initial) {
            keyName = getKeyName(thisRef, property)
            state = preferenceCache.getValue(
                key = keyName,
                default = defaultValue
            )
            initial = true
        }
        return state
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        if (keyName.isBlank()) {
            this.keyName = getKeyName(thisRef, property)
        }

        this.state = value
        classScope.launch { preferenceCache.setValue(keyName, state) }
    }

    private fun getKeyName(thisRef: Any, property: KProperty<*>): String {
        if (argumentKey.isNotBlank()) {
            return argumentKey
        } else {
            return "${thisRef.javaClass.simpleName}_${property.name}"
        }
    }

}
