package com.durgesh.mvvm_pattern.selecter

import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import com.durgesh.mvvm_pattern.database.entity.Habits
import com.durgesh.mvvm_pattern.ui.dblist.HabitListAdapter

class SheetDetailsLookup internal constructor(private val recyclerView: RecyclerView) : ItemDetailsLookup<Habits>() {

    override fun getItemDetails(e: MotionEvent): ItemDetailsLookup.ItemDetails<Habits>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        if (view != null) {
            val holder = recyclerView.getChildViewHolder(view)
            if (holder is HabitListAdapter.HabitViewHolder)
                return holder.getItemDetails()

        }
        return null
    }
}