package com.ws.eyepetizer.provider.http

import com.ws.eyepetizer.common.utils.SPUtil
import com.ws.lib.restful.HiRestful

object ApiFactory {
    val KEY_DEGRADE_HTTP = "degrade_http"
    val HTTPS_BASE_URL = "https://baobab.kaiyanapp.com/api/"
    val HTTP_BASE_URL = "http://baobab.kaiyanapp.com/api/"
    val degrade2Http = SPUtil.getBoolean(KEY_DEGRADE_HTTP)
    val baseUrl = if (degrade2Http) HTTP_BASE_URL else HTTPS_BASE_URL
    private val hiRestful: HiRestful = HiRestful(baseUrl, RetrofitCallFactory(baseUrl))

    init {
        SPUtil.putBoolean(KEY_DEGRADE_HTTP, false)
    }

    fun <T> create(service: Class<T>): T {
        return hiRestful.create(service)
    }
}