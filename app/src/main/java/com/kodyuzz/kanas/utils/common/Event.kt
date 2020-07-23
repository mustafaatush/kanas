package com.kodyuzz.kanas.utils.common

import java.util.concurrent.atomic.AtomicBoolean

data class Event<out T>(private val content: T) {
    private var hasBeenHandled = AtomicBoolean(false)

    fun getIfNotHandled(): T? = if (hasBeenHandled.getAndSet(true)) null else content

    fun peek(): T = content
}