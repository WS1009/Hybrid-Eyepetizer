package com.ws.eyepetizer.daily.floor

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ws.eyepetizer.common.ext.loadUrl
import com.ws.eyepetizer.daily.R
import com.ws.eyepetizer.provider.model.Item
import com.ws.lib.util.HiDisplayUtil
import com.ws.lib.util.HiRes
import com.ws.ui.banner.HiBanner
import com.ws.ui.banner.core.HiBannerMo
import com.ws.ui.item.HiDataItem
import com.ws.ui.item.HiViewHolder

class BannerItem(val list: List<Item>) : HiDataItem<List<Item>, HiViewHolder>(list) {

    override fun onBindData(holder: HiViewHolder, position: Int) {
        val context = holder.itemView.context
        val banner = holder.itemView as HiBanner

        val models = mutableListOf<HiBannerMo>()
        list.forEachIndexed { _, homeBanner ->
            val bannerMo = object : HiBannerMo() {}
            bannerMo.url = homeBanner.data.cover?.feed
            models.add(bannerMo)
        }
        banner.setOnBannerClickListener { viewHolder, bannerMo, position ->
            val homeBanner = list[position]
        }
        banner.setBannerData(models)
        banner.setBindAdapter { viewHolder, mo, position ->
            ((viewHolder.rootView) as ImageView).loadUrl(mo.url)
        }
    }

    override fun getItemView(parent: ViewGroup): View? {
        val context = parent.context
        val banner = HiBanner(context)
        val params = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            HiDisplayUtil.dp2px(200f)
        )
        params.bottomMargin = HiDisplayUtil.dp2px(10f)
        params.leftMargin = HiDisplayUtil.dp2px(10f)
        params.rightMargin = HiDisplayUtil.dp2px(10f)
        banner.layoutParams = params
        banner.setBackgroundColor(HiRes.getColor(R.color.color_white))
        return banner
    }

    override fun onViewAttachedToWindow(holder: HiViewHolder) {
        super.onViewAttachedToWindow(holder)
        val itemView = holder.itemView
        val layoutParams = itemView.layoutParams
        layoutParams.height = (HiDisplayUtil.getDisplayWidthInPx(itemView.context) / 2.0).toInt()
        itemView.layoutParams = layoutParams
    }
}