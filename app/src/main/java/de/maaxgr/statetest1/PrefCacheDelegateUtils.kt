package de.maaxgr.statetest1

fun <T: Any> appProperty(defaultValue: T) = PrefCacheDelegate<T>("", defaultValue)