package com.example.mvvmproject.net

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
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
abstract class NetWorkHandler<ResultType> constructor(private val appExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<ResultType>) = response.body

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<ResultType>>

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }


    init {
        setValue(Resource.loading(null))
        fetchFromNetwork()
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

}