package com.ws.eyepetizer.daily.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ws.eyepetizer.common.component.HiAbsListFragment
import com.ws.eyepetizer.common.ext.otherwise
import com.ws.eyepetizer.common.ext.yes
import com.ws.eyepetizer.daily.floor.BannerItem
import com.ws.eyepetizer.daily.viewmodel.DailyViewModel
import com.ws.eyepetizer.provider.model.Item
import com.ws.ui.item.HiDataItem

class DailyListFragment : HiAbsListFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enableLoadMore {
            loadData()
        }
    }

    override fun loadData() {
        val dailyViewModel = ViewModelProvider(this)[DailyViewModel::class.java]
        dailyViewModel.getDailyBanner().observe(viewLifecycleOwner, { it ->
            val itemList = it?.isNotEmpty()?.yes {
                it[0].itemList
            }?.otherwise {
                mutableListOf()
            }

            itemList?.removeAll { item ->
                item.type == "banner2"
            }

            itemList?.isNotEmpty()?.yes {
                updateUI(itemList)
            }?.otherwise {
                finishRefresh(null)
            }
        })
    }

    override fun onRefresh() {
        super.onRefresh()
        loadData()
    }

    override fun createLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    private fun updateUI(itemList: MutableList<Item>) {
        val dataItems = mutableListOf<HiDataItem<*, *>>()
        dataItems.add(BannerItem(itemList))
        finishRefresh(dataItems)
    }
}