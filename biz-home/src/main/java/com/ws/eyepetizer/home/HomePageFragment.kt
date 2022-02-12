package com.ws.eyepetizer.home

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.ws.eyepetizer.common.component.HiBaseFragment
import com.ws.eyepetizer.provider.RouterPath
import kotlinx.android.synthetic.main.fragment_home.*

class HomePageFragment : HiBaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn.setOnClickListener {
            ARouter.getInstance().build(RouterPath.Hot.PATH_HOT_HOME).navigation();
        }
    }
}