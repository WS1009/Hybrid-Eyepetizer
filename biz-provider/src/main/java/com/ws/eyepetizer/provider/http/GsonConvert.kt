package com.ws.eyepetizer.provider.http

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ws.lib.restful.HiConvert
import com.ws.lib.restful.HiResponse
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Type

class GsonConvert : HiConvert {
    private var gson = Gson()

    override fun <T> convert(rawData: String, dataType: Type): HiResponse<T> {
        val response: HiResponse<T> = HiResponse<T>()
        try {
            val jsonObject = JSONObject(rawData)
            response.code = jsonObject.optInt("code")
            response.msg = jsonObject.optString("msg")
            if ((jsonObject is JSONObject) or (jsonObject is JSONArray)) {
                if (response.code == HiResponse.SUCCESS) {
                    response.data = gson.fromJson(rawData, dataType)
                } else {
                    response.errorData = gson.fromJson<MutableMap<String, String>>(
                        rawData,
                        object : TypeToken<MutableMap<String, String>>() {}.type
                    )
                }
            } else {
                response.data = jsonObject as? T
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            response.code = -1
            response.msg = e.message
        }

        response.rawData = rawData
        return response
    }

}