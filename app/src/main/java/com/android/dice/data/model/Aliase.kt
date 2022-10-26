package com.android.dice.data.model

import com.google.gson.annotations.SerializedName

data class Aliase(
    @SerializedName("begin-date")
    val beginDate: Any?,
    @SerializedName("end-date")
    val endDate: Any?,
    @SerializedName("locale")
    val locale: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("primary")
    val primary: Any?,
    @SerializedName("sort-name")
    val sortName: String,
    @SerializedName("type")
    val type: String?,
    @SerializedName("type-id")
    val typeId: String?
)
