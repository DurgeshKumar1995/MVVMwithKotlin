package com.durgesh.mvvm_pattern.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val login: String):Parcelable
