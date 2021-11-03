package com.reno.coinlistapp.common

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.reno.coinlistapp.R
import com.reno.coinlistapp.common.ViewState.ERROR
import com.reno.coinlistapp.common.ViewState.LOADING
import com.reno.coinlistapp.common.ViewState.SUCCESS

@BindingAdapter("visibility_by_viewState")
fun isVisibleByViewState(view: View, visibleViewState: LiveData<ViewState>) {
    val viewState = visibleViewState.value ?: return

    when (view.id) {
        R.id.tvError -> {
            view.isVisible = viewState == ERROR
        }
        R.id.progressBar -> {
            view.isVisible = viewState == LOADING
        }
        else -> {
            view.isVisible = viewState == SUCCESS
        }
    }
}
