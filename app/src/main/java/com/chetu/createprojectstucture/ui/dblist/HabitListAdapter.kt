package com.chetu.createprojectstucture.ui.dblist

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.selection.SelectionTracker
import com.chetu.createprojectstucture.R
import com.chetu.createprojectstucture.database.entity.Habits
import com.chetu.createprojectstucture.selecter.SheetItemDetails
import com.chetu.createprojectstucture.util.CommanWord
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class HabitListAdapter internal constructor(context: Context) : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    private val mInflater: LayoutInflater
    private var habitsList: List<Habits>? = null
    private var  publishSubject :PublishSubject<Habits> = PublishSubject.create()
    val clickEvent: Observable<Habits>

    init {
        mInflater = LayoutInflater.from(context)
        clickEvent=publishSubject

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return HabitViewHolder(itemView)
    }

    @SuppressLint(CommanWord.HARDCODED_EXCEPETIN)
    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {

        val item = habitsList?.get(position)

        holder.bindView(item, mSelectionTracker?.isSelected(item))

    }

    override fun getItemCount(): Int {
        return if (habitsList != null)
            habitsList!!.size
        else
            CommanWord.ZeroInt
    }

    internal fun setWords(habitsList: List<Habits>) {
        this.habitsList = habitsList
        notifyDataSetChanged()
    }

    inner class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val habitItemView: TextView
        val selected: ImageView

        init {
            habitItemView = itemView.findViewById(R.id.textView)
            selected = itemView.findViewById(R.id.selected)
            itemView.setOnClickListener {
                try {
                    publishSubject.onNext(habitsList?.get(adapterPosition)!!)
                }catch (e : Exception){

                }

            }
        }
        fun getItemDetails(): SheetItemDetails? {
             try {
                 return SheetItemDetails(adapterPosition, habitsList?.get(adapterPosition)!!)
            }catch (e:Exception){
               return null
            }
        }

        fun bindView(habits: Habits?,boolean: Boolean?){

            if (habits != null) {
               habitItemView.text = habits.habit
            } else {
                habitItemView.text = "No Word"
            }


            if(boolean!!){
                selected.visibility=View.VISIBLE
            }else {
                selected.visibility=View.GONE
            }
        }
    }
    private var mSelectionTracker: SelectionTracker<Habits>? = null

    fun setSelectionTracker(mSelectionTracker: SelectionTracker<Habits>) {
        this.mSelectionTracker = mSelectionTracker
    }
}