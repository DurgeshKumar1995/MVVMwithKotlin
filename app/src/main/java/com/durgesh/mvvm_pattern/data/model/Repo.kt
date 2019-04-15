package com.durgesh.mvvm_pattern.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repo(
    val id: Long, val name: String, val description: String, val owner: User, @field:SerializedName("stargazers_count")
    val stars: Long, @field:SerializedName("forks_count")
    val forks: Long
):Parcelable
