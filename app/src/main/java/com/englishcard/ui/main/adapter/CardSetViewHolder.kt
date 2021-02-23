package com.englishcard.ui.main.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.englishcard.R

interface CardSetListener {
    fun onCardSetClicked(adapterPosition: Int)
}

class CardSetViewHolder(view: View, cardSetListener: CardSetListener): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.card_set_textview)
        val languageTextView: TextView = view.findViewById(R.id.card_set_language)

        init {
            view.setOnClickListener {
                cardSetListener.onCardSetClicked(adapterPosition)
            }
        }
}