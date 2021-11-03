package com.reno.coinlistapp.coin_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reno.coinlistapp.common.ViewState
import com.reno.coinlistapp.common.ViewState.ERROR
import com.reno.coinlistapp.common.ViewState.LOADING
import com.reno.coinlistapp.common.ViewState.SUCCESS
import com.reno.coinlistapp.data.CoinRepository
import com.reno.coinlistapp.data.model.CoinDetail
import com.reno.coinlistapp.data.model.Result.Error
import com.reno.coinlistapp.data.model.Result.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    private val _coinDetail = MutableLiveData<CoinDetail>()
    val coinDetail: LiveData<CoinDetail>
        get() = _coinDetail

    fun loadCoinDetail(id: String) {
        viewModelScope.launch {
            _viewState.value = LOADING

            when (val result = coinRepository.getCoinDetails(id)) {
                is Success -> {
                    _viewState.value = SUCCESS
                    _coinDetail.postValue(result.data)
                }
                is Error -> {
                    result.exception.printStackTrace()
                    _viewState.value = ERROR
                }
            }

        }
    }

}