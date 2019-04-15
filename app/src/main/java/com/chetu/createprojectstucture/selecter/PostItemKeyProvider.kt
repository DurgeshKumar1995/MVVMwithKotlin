package com.chetu.createprojectstucture.selecter


import androidx.recyclerview.selection.ItemKeyProvider
import com.chetu.createprojectstucture.database.entity.Habits

internal class PostItemKeyProvider(scope: Int, private val items: List<Habits>) : ItemKeyProvider<Habits>(scope) {


    override fun getKey(i: Int): Habits? {
        return items[i]
    }

    override fun getPosition(post: Habits): Int {
        return items.indexOf(post)
    }
}