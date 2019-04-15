package com.durgesh.mvvm_pattern.selecter


import androidx.recyclerview.selection.ItemKeyProvider
import com.durgesh.mvvm_pattern.database.entity.Habits

internal class PostItemKeyProvider(scope: Int, private val items: List<Habits>) : ItemKeyProvider<Habits>(scope) {


    override fun getKey(i: Int): Habits? {
        return items[i]
    }

    override fun getPosition(post: Habits): Int {
        return items.indexOf(post)
    }
}