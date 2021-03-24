package com.englishcard.ui.main.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.englishcard.R


interface CardListener {
    fun onCardClicked(adapterPosition: Int)
}

class CardViewHolder(view: View, cardListener: CardListener): RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.card_text)
    val card: View = view.findViewById(R.id.card_view)

    init {
        card.setOnClickListener {
            cardListener.onCardClicked(adapterPosition)
        }
    }
}