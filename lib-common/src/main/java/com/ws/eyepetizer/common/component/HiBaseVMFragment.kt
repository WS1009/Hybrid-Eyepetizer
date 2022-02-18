package com.ws.eyepetizer.common.component

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.ws.eyepetizer.common.viewmodel.BaseViewModel
import com.ws.eyepetizer.common.viewmodel.ErrorState
import com.ws.eyepetizer.common.viewmodel.LoadState
import com.ws.eyepetizer.common.viewmodel.SuccessState
import java.lang.reflect.ParameterizedType

abstract class HiBaseVMFragment<VM : BaseViewModel> : HiBaseFragment() {

    private lateinit var mViewModel: VM

    override fun loadData() {
        initViewModel()
        lazyLoadData()
    }

    private fun initViewModel() {
        val parameterizedType = javaClass.genericSuperclass as ParameterizedType
        mViewModel = ViewModelProvider(this)[parameterizedType.actualTypeArguments[0] as Class<VM>]
        mViewModel.mStateLiveData.observe(viewLifecycleOwner, { state ->
            when (state) {
                LoadState -> {
                    showLoading()
                }
                SuccessState -> {
                    hideLoading()
                }
                is ErrorState -> {
                    hideLoading()
                    state.errorMsg?.let { errorToast(it) }
                    handlerError()
                }
            }
        })
    }

    private fun errorToast(it: String) {

    }

    protected fun <T : Any> LiveData<T>.observerKt(block: (T) -> Unit) {
        this.observe(viewLifecycleOwner) {
            block(it)
        }
    }

    //由于每个页面的加载框可能不一致，所以此处暴露给子类实现
    open fun showLoading() {

    }

    open fun hideLoading() {

    }

    open fun handlerError() {

    }

    abstract fun lazyLoadData()
}