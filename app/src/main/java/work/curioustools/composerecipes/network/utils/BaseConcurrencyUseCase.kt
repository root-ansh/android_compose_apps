package work.curioustools.composerecipes.network.utils

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseConcurrencyUseCase<REQUEST, RESP:Any> {
    private val job = Job()
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    val responseLiveData = MutableLiveData<RESP>()

    abstract suspend fun getRepoCall(param: REQUEST): RESP

    fun requestForData(param: REQUEST) {
        scope.apply {
            launch(Dispatchers.IO + job) {
                val result: RESP = getRepoCall(param)
                responseLiveData.postValue(result)
            }
        }
    }

    fun cancelRequest(){
        job.cancel()
    }

}