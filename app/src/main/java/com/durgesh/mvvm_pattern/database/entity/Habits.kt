package com.durgesh.mvvm_pattern.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.durgesh.mvvm_pattern.util.CommanWord
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = CommanWord.TableName)
data class Habits(
    @PrimaryKey
    @ColumnInfo(name = CommanWord.ColumanName) val habit: String
):Parcelable
