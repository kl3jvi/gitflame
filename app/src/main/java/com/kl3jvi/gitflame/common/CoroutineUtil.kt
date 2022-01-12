package com.kl3jvi.gitflame.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

fun CoroutineScope.launchOnDefault(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = launch(Dispatchers.Default, start, block)

fun CoroutineScope.launchOnIo(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = launch(Dispatchers.IO, start, block)

// Im so proud of this piece of code :D
fun <T> Flow<Resource<T>>.mapToState(): Flow<State<T>> {
    return map { resource ->
        State.fromResource(resource)
    }
}

