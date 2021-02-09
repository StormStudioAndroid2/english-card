package com.englishcard.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.englishcard.R
import com.englishcard.model.cardset.CardSetEntity

class CardSetAdapter(private var dataSet: List<CardSetEntity>) : RecyclerView.Adapter<CardSetAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.card_set_textview)
        val languageTextView: TextView = view.findViewById(R.id.card_set_language)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_card_set, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position].name
        viewHolder.languageTextView.text = dataSet[position].language
    }

    override fun getItemCount() = dataSet.size

    fun update(data: List<CardSetEntity>) {
        this.dataSet = data
        notifyDataSetChanged()
    }
}