package com.ws.eyepetizer;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentManager;

import com.ws.eyepetizer.common.tab.HiFragmentTabView;
import com.ws.eyepetizer.common.tab.HiTabViewAdapter;
import com.ws.eyepetizer.home.HomePageFragment;
import com.ws.eyepetizer.hot.HotPageFragment;
import com.ws.lib.util.HiViewUtil;
import com.ws.ui.tab.bottom.HiTabBottomInfo;
import com.ws.ui.tab.bottom.HiTabBottomLayout;
import com.ws.ui.tab.common.IHiTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivityLogic {
    private ActivityProvider activityProvider;
    private int currentItemIndex;
    private HiFragmentTabView fragmentTabView;
    private HiTabBottomLayout hiTabBottomLayout;
    private List<HiTabBottomInfo<?>> infoList;
    private final static String SAVED_CURRENT_ID = "SAVED_CURRENT_ID";

    public MainActivityLogic(ActivityProvider activityProvider, @Nullable Bundle savedInstanceState) {
        this.activityProvider = activityProvider;
        //fix 不保留活动导致的Fragment重叠问题
        if (savedInstanceState != null) {
            currentItemIndex = savedInstanceState.getInt(SAVED_CURRENT_ID);
        }

        initTabBottom();
    }

    private void initTabBottom() {
        hiTabBottomLayout = activityProvider.findViewById(R.id.tab_bottom_layout);
        hiTabBottomLayout.setTabAlpha(HiViewUtil.lightMode() ? 0.85f : 0f);

        infoList = new ArrayList<>();
        int defaultColor = activityProvider.getResources().getColor(R.color.tabBottomDefaultColor);
        int tintColor = activityProvider.getResources().getColor(R.color.tabBottomTintColor);

        HiTabBottomInfo homeInfo = new HiTabBottomInfo<Integer>(
                "首页",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_home),
                null,
                defaultColor,
                tintColor
        );
        homeInfo.fragment = HomePageFragment.class;

        HiTabBottomInfo hotInfo = new HiTabBottomInfo<Integer>(
                "热点",
                "fonts/iconfont.ttf",
                activityProvider.getString(R.string.if_category),
                null,
                defaultColor,
                tintColor
        );
        hotInfo.fragment = HotPageFragment.class;


        infoList.add(homeInfo);
        infoList.add(hotInfo);
        hiTabBottomLayout.inflateInfo(infoList);

        initFragmentTabView();

        hiTabBottomLayout.addTabSelectedChangeListener(new IHiTabLayout.OnTabSelectedListener<HiTabBottomInfo<?>>() {
            @Override
            public void onTabSelectedChange(int index, @Nullable HiTabBottomInfo<?> prevInfo, @NonNull HiTabBottomInfo<?> nextInfo) {
                fragmentTabView.setCurrentItem(index);
                MainActivityLogic.this.currentItemIndex = index;
            }
        });

        hiTabBottomLayout.defaultSelected(infoList.get(currentItemIndex));
    }

    private void initFragmentTabView() {
        HiTabViewAdapter tabViewAdapter = new HiTabViewAdapter(activityProvider.getSupportFragmentManager(), infoList);
        fragmentTabView = activityProvider.findViewById(R.id.fragment_tab_view);
        fragmentTabView.setAdapter(tabViewAdapter);
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SAVED_CURRENT_ID, currentItemIndex);
    }

    public interface ActivityProvider {
        <T extends View> T findViewById(@IdRes int id);

        Resources getResources();

        FragmentManager getSupportFragmentManager();

        String getString(@StringRes int resId);

        Window getWindow();
    }
}
