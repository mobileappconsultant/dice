package com.android.dice.data.model

import com.google.gson.annotations.SerializedName

data class LifeSpan(
    @SerializedName("begin")
    val begin: String?,
    @SerializedName("end")
    val end: String?,
    @SerializedName("ended")
    val ended: Boolean?
)
