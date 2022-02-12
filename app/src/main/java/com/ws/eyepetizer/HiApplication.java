package com.ws.eyepetizer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ws.eyepetizer.common.component.HiBaseApplication;

public class HiApplication extends HiBaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
            ARouter.printStackTrace();
        }

        ARouter.init(this);
    }
}
