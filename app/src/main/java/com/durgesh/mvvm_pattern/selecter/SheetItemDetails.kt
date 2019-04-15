package com.durgesh.mvvm_pattern.selecter


import androidx.recyclerview.selection.ItemDetailsLookup
import com.durgesh.mvvm_pattern.database.entity.Habits

class SheetItemDetails internal constructor(private val position: Int, private val key: Habits) : ItemDetailsLookup.ItemDetails<Habits>() {

    override fun getPosition(): Int {
        return position
    }

    override fun getSelectionKey(): Habits? {
        return key
    }
}