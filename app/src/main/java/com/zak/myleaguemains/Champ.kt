package com.zak.myleaguemains

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Champ(
    val name: String,
    val alias: String,
    val splashart: Int,
    val description: String,
    val quotes: String
) : Parcelable
