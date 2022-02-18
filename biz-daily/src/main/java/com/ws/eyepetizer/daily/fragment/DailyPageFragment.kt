package com.ws.eyepetizer.daily.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.ws.eyepetizer.common.component.HiBaseFragment
import com.ws.eyepetizer.daily.R

class DailyPageFragment : HiBaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_daily
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transaction: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
        transaction?.add(R.id.fragment_container, DailyListFragment())
        transaction?.commit()
    }
}