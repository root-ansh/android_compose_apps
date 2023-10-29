package work.curioustools.composerecipes.network.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.flowObserve(scope: CoroutineScope, onDataAvailable: (T) -> Unit) {
    onEach { onDataAvailable.invoke(it) }.launchIn(scope)
}
fun <T> mutableResponseState(): MutableState<ApiResponse<T>> = mutableStateOf(ApiResponse.Loading())
