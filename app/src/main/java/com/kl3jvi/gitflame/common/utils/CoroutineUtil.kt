package com.kl3jvi.gitflame.common.utils


import androidx.lifecycle.*
import com.kl3jvi.gitflame.common.network_state.Resource
import com.kl3jvi.gitflame.common.network_state.State
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map

fun CoroutineScope.launchOnDefault(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = launch(Dispatchers.Default, start, block)

fun CoroutineScope.launchOnIo(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = launch(Dispatchers.IO, start, block)

fun <T> Flow<Resource<T>>.mapToState(): Flow<State<T>> = map { resource ->
    State.fromResource(resource)
}

inline fun <T> LifecycleOwner.collectFlow(
    flow: Flow<T>,
    crossinline collector: suspend (T) -> Unit
) {
    lifecycleScope.launchWhenStarted {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect {
                collector(it)
            }
        }
    }
}

inline fun <T> LifecycleOwner.collectLatestFlow(
    flow: Flow<T>,
    crossinline collector: suspend (T) -> Unit
) {
    lifecycleScope.launchWhenStarted {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest {
                collector(it)
            }
        }
    }
}

fun ViewModel.launchOnIo(coroutineScope: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch {
        coroutineScope(this)
    }
}

fun <T> observeLiveData(
    liveData: LiveData<T>,
    owner: LifecycleOwner,
    observer: (T) -> Unit
) {
    liveData.observe(owner) {
        observer(it)
    }
}


