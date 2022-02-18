package com.ws.eyepetizer

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ws.lib.util.HiStatusBar

class MainActivity : AppCompatActivity(),
    MainActivityLogic.ActivityProvider {
    private var activityLogic: MainActivityLogic? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityLogic = MainActivityLogic(this, savedInstanceState)

        HiStatusBar.setStatusBar(this, true, Color.WHITE, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        activityLogic?.onSaveInstanceState(outState)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragments = supportFragmentManager.fragments
        for (fragment in fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}