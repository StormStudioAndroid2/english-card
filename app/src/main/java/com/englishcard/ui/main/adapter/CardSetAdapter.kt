package com.englishcard.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.englishcard.R
import com.englishcard.model.database.CardSetEntity
import com.englishcard.model.domain.cards.CardSet

class CardSetAdapter(private var dataSet: List<CardSet>, val cardSetListener: CardSetListener) : RecyclerView.Adapter<CardSetViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardSetViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_card_set, viewGroup, false)
        return CardSetViewHolder(view, cardSetListener)
    }

    override fun onBindViewHolder(viewHolder:CardSetViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position].name
        viewHolder.languageTextView.text = dataSet[position].language
        viewHolder.itemView.setOnClickListener { cardSetListener.onCardSetClicked(adapterPosition = position) }
    }

    override fun getItemCount() = dataSet.size

    fun update(data: List<CardSet>) {
        this.dataSet = data
        notifyDataSetChanged()
    }
}