package com.example.mvvmproject.net

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.android.example.github.AppExecutors
import com.example.mvvmproject.api.ApiEmptyResponse
import com.example.mvvmproject.api.ApiErrorResponse
import com.example.mvvmproject.api.ApiResponse
import com.example.mvvmproject.api.ApiSuccessResponse
import com.example.mvvmproject.model.entity.Resource

/**
 *    author : desperado
 *    e-mail : foreverxiongtao@sina.com
 *    date   : 2019/6/27 下午3:01
 *    desc   : 网络加载处理类
 *    version: 1.0
 */
abstract class NetWorkHandler<RequestType, ResultType> constructor(private val appExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<ResultType>) = response.body

//    @WorkerThread
//    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
//    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun shouldFetch(): Boolean

//    @MainThread
//    protected abstract fun loadFromDb(): LiveData<ResultType>?

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<ResultType>>

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

//    init {
//        result.value = Resource.loading(null)
//        @Suppress("LeakingThis")
//        val dbSource = loadFromDb()
//        dbSource?.let {
//            result.addSource(dbSource) { data ->
//                result.removeSource(dbSource)
//                if (shouldFetch(data)) {
//                    fetchFromNetwork(dbSource)
//                } else {
//                    result.addSource(dbSource) { newData ->
//                        setValue(Resource.success(newData))
//                    }
//                }
//            }
//        } ?: run {
//        }
//
//
//    }


    init {
        setValue(Resource.loading(null))
        if (shouldFetch()) {
            fetchFromNetwork()
        }
    }


    private fun fetchFromNetwork() {
        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->
            when (response) {
                is ApiSuccessResponse -> {
                    val body = processResponse(response)
                    appExecutors.diskIO().execute {
                        appExecutors.mainThread().execute {

                            setValue(Resource.success(body))
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    appExecutors.mainThread().execute {
                        // reload from disk whatever we had
                        setValue(Resource.empty())
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    setValue(Resource.error(response.errorMessage))
                }
            }
        }
    }


//    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
//        val apiResponse = createCall()
//        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
//        result.addSource(dbSource) { newData ->
//            setValue(Resource.loading(newData))
//        }
//        result.addSource(apiResponse) { response ->
//            result.removeSource(apiResponse)
//            result.removeSource(dbSource)
//            when (response) {
//                is ApiSuccessResponse -> {
//                    appExecutors.diskIO().execute {
//                        saveCallResult(processResponse(response))
//                        appExecutors.mainThread().execute {
//                            // we specially request a new live data,
//                            // otherwise we will get immediately last cached value,
//                            // which may not be updated with latest results received from network.
//                            result.addSource(loadFromDb()) { newData ->
//                                setValue(Resource.success(newData))
//                            }
//                        }
//                    }
//                }
//                is ApiEmptyResponse -> {
//                    appExecutors.mainThread().execute {
//                        // reload from disk whatever we had
//                        result.addSource(loadFromDb()) { newData ->
//                            setValue(Resource.success(newData))
//                        }
//                    }
//                }
//                is ApiErrorResponse -> {
//                    onFetchFailed()
//                    result.addSource(dbSource) { newData ->
//                        setValue(Resource.error(response.errorMessage, newData))
//                    }
//                }
//            }
//        }
//    }
}