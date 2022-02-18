package com.ws.eyepetizer.daily.api

import com.ws.eyepetizer.daily.model.DailyModel
import com.ws.eyepetizer.provider.model.Issue
import com.ws.lib.restful.HiCall
import com.ws.lib.restful.annotation.Filed
import com.ws.lib.restful.annotation.GET
import com.ws.lib.restful.annotation.Url


interface DailyApi {

    @GET("v2/feed?num=1")
    fun getDailyBanner(): HiCall<DailyModel>

    @GET("")
    fun getDailyList(@Url url: String): HiCall<DailyModel>

    @GET("v3/queries/hot")
    suspend fun getKeyWordList(): List<String>

    @GET("v1/search")
    suspend fun searchVideoList(@Filed("query") keyword: String): Issue

    @GET("")
    suspend fun getMoreSearchVideoList(@Url url: String): Issue

}