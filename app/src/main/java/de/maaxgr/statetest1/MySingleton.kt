package de.maaxgr.statetest1

import de.maaxgr.statetest1.pref.AndroidPreference
import kotlinx.coroutines.*

class MySingleton {

    private val prefs = AndroidPreference()
    private val classScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    var globalState: String? = prefs.getStringPref("abc")
        set(value) {
            field = value
            value?.let { prefs.setStringPref("abc", it) }
        }

    var globalState2: String by appProperty("")

    data class ComplexStateObject(val simpleString: String) {
        companion object {
            fun default() = ComplexStateObject("")
        }
    }

    private val appStateOld = AppState("xx", ComplexStateObject.default())
    private val appState by appState(ComplexStateObject.default())

    var globalState3: String
        get() {
            return appState.getFlow().value.simpleString
        }
        set(value) {
            classScope.launch { appState.write { it.copy(simpleString = value) } }
        }

}