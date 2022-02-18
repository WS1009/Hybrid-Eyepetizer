package com.ws.eyepetizer.daily.viewmodel

import androidx.lifecycle.*
import com.ws.eyepetizer.common.viewmodel.BaseViewModel
import com.ws.eyepetizer.daily.api.DailyApi
import com.ws.eyepetizer.daily.model.DailyModel
import com.ws.eyepetizer.provider.http.ApiFactory
import com.ws.eyepetizer.provider.model.Issue
import com.ws.lib.log.HiLog
import com.ws.lib.log.HiLogConfig
import com.ws.lib.restful.HiCall
import com.ws.lib.restful.HiCallback
import com.ws.lib.restful.HiResponse

class DailyViewModel : BaseViewModel() {
    var mNextPageUrl = MutableLiveData<String>()

    fun getDailyBanner(): LiveData<List<Issue>?> {
        val dailyBanner = MutableLiveData<List<Issue>?>()
        ApiFactory.create(DailyApi::class.java).getDailyBanner()
            .enqueue(object : HiCallback<DailyModel> {
                override fun onSuccess(response: HiResponse<DailyModel>) {
                    if (response.successful() && response.data != null) {
                        mNextPageUrl.postValue(response.data?.nextPageUrl)
                        val itemList = response.data?.issueList
                        dailyBanner.postValue(itemList)
                    } else {
                        dailyBanner.postValue(null)
                    }
                }

                override fun onFailed(throwable: Throwable) {

                }
            })
        return dailyBanner
    }

    fun getDailyList(): LiveData<DailyModel?> {
        val dailyList = MutableLiveData<DailyModel?>()
        mNextPageUrl.value?.also {
            ApiFactory.create(DailyApi::class.java).getDailyList(it)
                .enqueue(object : HiCallback<DailyModel> {
                    override fun onSuccess(response: HiResponse<DailyModel>) {
                        if (response.successful() && response.data != null) {
                            dailyList.postValue(response.data)
                        } else {
                            dailyList.postValue(null)
                        }
                    }

                    override fun onFailed(throwable: Throwable) {
                        dailyList.postValue(null)
                    }
                })
        }
        return dailyList
    }
}