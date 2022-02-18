package com.ws.eyepetizer.common.component

import android.app.Application
import com.google.gson.Gson
import com.ws.lib.log.HiConsolePrinter
import com.ws.lib.log.HiFilePrinter
import com.ws.lib.log.HiLogConfig
import com.ws.lib.log.HiLogManager

open class HiBaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initLog()
    }

    private fun initLog() {
        HiLogManager.init(object : HiLogConfig() {
            override fun injectJsonParser(): JsonParser {
                return JsonParser { src: Any? -> Gson().toJson(src) }
            }

            override fun includeThread(): Boolean {
                return true
            }
        }, HiConsolePrinter(), HiFilePrinter.getInstance(cacheDir.absolutePath, 0))
    }

}