package com.englishcard.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.englishcard.R
import com.englishcard.model.database.CardEntity
import com.englishcard.model.domain.cards.Card

class CardAdapter(private var dataSet: List<Card>, val cardListener: CardListener) : RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_card_set, viewGroup, false)
        return CardViewHolder(view, cardListener)
    }

    override fun onBindViewHolder(viewHolder:CardViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position].translateWord
        viewHolder.itemView.setOnClickListener { cardListener.onCardClicked(adapterPosition = position) }
    }

    override fun getItemCount() = dataSet.size

    fun update(data: List<Card>) {
        this.dataSet = data
        notifyDataSetChanged()
    }
}