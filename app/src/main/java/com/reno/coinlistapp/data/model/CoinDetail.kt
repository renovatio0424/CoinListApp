package com.reno.coinlistapp.data.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class CoinDetail(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    //yyyy-MM-dd'T'hh:mm:ssZ
    @SerializedName("first_data_at")
    private val firstDataDate: Date
) {
    val simpleFirstDataAt: String
        get() {
            return SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(firstDataDate)
        }
}