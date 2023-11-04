package work.curioustools.composerecipes.network.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseConcurrencyUseCase<REQUEST, RESP : Any> {
    abstract suspend fun getRepoCall(param: REQUEST): RESP
    fun requestForDataAsFlow(param: REQUEST): Flow<RESP> {
        return flow { this.emit(getRepoCall(param)) }.flowOn(Dispatchers.IO)
    }
}