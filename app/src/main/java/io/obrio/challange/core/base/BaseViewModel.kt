package io.obrio.challange.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Effect>(state: State): ViewModel() {

    private val errorFlow = MutableSharedFlow<Throwable>()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
       viewModelScope.launch {
           errorFlow.emit(throwable)
       }
    }

    protected val viewModelScope: CoroutineScope by lazy {
        CoroutineScope(SupervisorJob() + Dispatchers.IO + errorHandler)
    }

    private val stateFlow = MutableStateFlow(state)
    val state: StateFlow<State> = stateFlow.asStateFlow()

    private val effectChannel = Channel<Effect>(Channel.BUFFERED)
    val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    protected fun updateState(transformation: State.() -> State) {
        stateFlow.update { currentState ->
            transformation(currentState)
        }
    }

    fun publishEffect(effect: Effect) {
        viewModelScope.launch { effectChannel.send(effect) }
    }

    fun getErrorFlow(): Flow<Throwable> {
        return errorFlow
    }

    override fun onCleared() {
        super.onCleared()

        viewModelScope.cancel()
    }
}