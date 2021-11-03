package com.reno.coinlistapp.common

enum class ViewState {
    LOADING, // coin api is requesting
    SUCCESS, // coin api is successfully responded
    ERROR    // coin api is failed to respond
}