package com.reno.coinlistapp.coin_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reno.coinlistapp.common.ViewState
import com.reno.coinlistapp.common.ViewState.ERROR
import com.reno.coinlistapp.common.ViewState.LOADING
import com.reno.coinlistapp.common.ViewState.SUCCESS
import com.reno.coinlistapp.data.CoinRepository
import com.reno.coinlistapp.data.model.Coin
import com.reno.coinlistapp.data.model.Result.Error
import com.reno.coinlistapp.data.model.Result.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private val _coinList = MutableLiveData<List<Coin>>()
    val coinList: LiveData<List<Coin>>
        get() = _coinList

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    init {
        loadCoinList()
    }

    private fun loadCoinList() {
        viewModelScope.launch {
            _viewState.value = LOADING

            when (val result = coinRepository.getCoinList()) {
                is Success -> {
                    _viewState.value = SUCCESS
                    _coinList.postValue(result.data)
                }
                is Error -> {
                    _viewState.value = ERROR
                    result.exception.printStackTrace()
                }
            }
        }
    }
}