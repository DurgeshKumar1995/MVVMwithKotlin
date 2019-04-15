package com.chetu.createprojectstucture.selecter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IsSelect( val string: String? ,var isSelect: Boolean? =false):Parcelable