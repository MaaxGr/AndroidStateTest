package de.maaxgr.statetest1

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.reflect.KProperty

class AppState<T: Any>(key : String, defaultValue: T) {

    private val mutex = Mutex()
    private var state: T by PrefCacheDelegate(key, defaultValue)
    private val flow: MutableStateFlow<T> = MutableStateFlow(state)

    fun getFlow() = flow.asStateFlow()

    suspend fun write(function: (T) -> T) {
        mutex.withLock {
            state = function(state)
            flow.value = state
        }
    }

}

inline fun <reified T: Any> appState(defaultValue: T) = AppStateDelegate<T>(defaultValue)

class AppStateDelegate<T: Any>(private val defaultValue: T) {

    private var initialized = false
    private lateinit var appState: AppState<T>

    init {

    }

    operator fun getValue(thisRef: Any, property: KProperty<*>): AppState<T> {
        if (!initialized) {
            val keyName = "${thisRef.javaClass.simpleName}_${property.name}"
            println("KEyname: $keyName")
            appState = AppState(keyName, defaultValue)
        }
        return appState
    }

}

