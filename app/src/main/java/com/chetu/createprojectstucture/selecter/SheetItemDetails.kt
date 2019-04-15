package com.chetu.createprojectstucture.selecter


import androidx.recyclerview.selection.ItemDetailsLookup
import com.chetu.createprojectstucture.database.entity.Habits

class SheetItemDetails internal constructor(private val position: Int, private val key: Habits) : ItemDetailsLookup.ItemDetails<Habits>() {

    override fun getPosition(): Int {
        return position
    }

    override fun getSelectionKey(): Habits? {
        return key
    }
}