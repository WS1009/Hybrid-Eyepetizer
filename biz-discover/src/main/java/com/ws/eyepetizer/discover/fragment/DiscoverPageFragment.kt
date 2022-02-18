package com.ws.eyepetizer.discover.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.ws.eyepetizer.common.component.HiBaseFragment
import com.ws.eyepetizer.discover.model.CategoryModel
import com.ws.eyepetizer.discover.viewmodel.DiscoverViewModel
import com.ws.eyepetizer.dscover.R
import com.ws.eyepetizer.provider.RouterPath
import com.ws.eyepetizer.provider.model.Issue
import com.ws.lib.log.HiLog
import com.ws.ui.tab.bottom.HiTabBottomLayout
import kotlinx.android.synthetic.main.fragment_discover.*
import kotlinx.coroutines.async

class DiscoverPageFragment : HiBaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_discover
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HiTabBottomLayout.clipBottomPadding(view_pager)
    }

    override fun loadData() {
        val viewModel = ViewModelProvider(this)[DiscoverViewModel::class.java]

        lifecycleScope.launchWhenStarted {
            val refreshListDeferred = async { viewModel.getRefreshList() }
            val categoryListDeferred = async { viewModel.getCategoryList() }
//            val refreshListLiveData = refreshListDeferred.await()
//            val categoryListLiveData = categoryListDeferred.await()

            updateUI(refreshListDeferred.await(), categoryListDeferred.await())

//            refreshListLiveData.observe(viewLifecycleOwner, {
//                HiLog.e("discover list", it.toString())
//            })
//
//            categoryListLiveData.observe(viewLifecycleOwner, {
//                HiLog.e("CategoryModel list", it.toString())
//            })

        }
    }

    private fun updateUI(await: LiveData<Issue>, await1: LiveData<List<CategoryModel>>) {
        await.observe(viewLifecycleOwner, {
            HiLog.e("discover list", it.toString())
        })

        await1.observe(viewLifecycleOwner, {
            HiLog.e("CategoryModel list", it.toString())
        })


//        HiLog.e("Issue list", await.value.toString())
//        HiLog.e("CategoryModel list", await1.value.toString())
    }

}