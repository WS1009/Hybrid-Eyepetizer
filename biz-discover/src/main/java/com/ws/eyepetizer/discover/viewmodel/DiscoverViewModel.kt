package com.ws.eyepetizer.discover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.liveData
import com.ws.eyepetizer.common.viewmodel.BaseViewModel
import com.ws.eyepetizer.discover.api.DiscoverApi
import com.ws.eyepetizer.discover.model.CategoryModel
import com.ws.eyepetizer.provider.http.NetConfig
import com.ws.eyepetizer.provider.model.Issue

class DiscoverViewModel : BaseViewModel() {

    private val discoverApi: DiscoverApi = NetConfig.Retrofit().create(DiscoverApi::class.java)

    suspend fun getRefreshList(): LiveData<Issue> = liveDataEx {
        val followList = discoverApi.getFollowList()
        followList
    }

    suspend fun getCategoryList(): LiveData<List<CategoryModel>> = liveDataEx {
        val categoryList = discoverApi.getCategoryList()
        categoryList
    }

}