package com.englishcard.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.englishcard.R
import com.englishcard.model.database.CardEntity
import com.englishcard.model.domain.cards.Card

class CardAdapter(private var dataSet: List<Card>) : RecyclerView.Adapter<CardViewHolder>(), CardListener {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_card, viewGroup, false)
        return CardViewHolder(view, this)
    }

    override fun onBindViewHolder(viewHolder: CardViewHolder, position: Int) {
        if (dataSet[position].isFrontShow) {
            viewHolder.textView.text = dataSet[position].frontWord
        } else {
            viewHolder.textView.text = dataSet[position].backWord
        }
    }

    override fun getItemCount() = dataSet.size

    fun update(data: List<Card>) {
        this.dataSet = data
        notifyDataSetChanged()
    }

    override fun onCardClicked(adapterPosition: Int) {
        dataSet[adapterPosition].isFrontShow = !dataSet[adapterPosition].isFrontShow
        notifyDataSetChanged()
    }
}