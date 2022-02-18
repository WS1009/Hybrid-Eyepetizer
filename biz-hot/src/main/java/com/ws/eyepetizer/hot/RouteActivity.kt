package com.ws.eyepetizer.hot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ws.eyepetizer.provider.RouterPath

@Route(path = RouterPath.Hot.PATH_ROUTE_HOME)
class RouteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)
    }
}