package com.englishcard.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.englishcard.R
import com.englishcard.model.domain.cards.Card

interface CardFragmentListener {

    fun onButtonSuccessClick(position: Int)

    fun onButtonFailClick(position: Int)
}

class CardFragment() : Fragment() {

    private lateinit var card: Card
    private var currentIndex: Int = 0
    private lateinit var cardFragmentListener: CardFragmentListener
    private lateinit var cardTextView: TextView
    private lateinit var cardContainer: View
    private lateinit var buttonSuccess: View
    private lateinit var buttonFail: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_card, container, false)

        savedInstanceState?.getParcelable<Card>("CARD_KEY")?.let {
            card = it
        }
        savedInstanceState?.getInt("INDEX_KEY")?.let {
            currentIndex = it
        }

        cardFragmentListener = (activity as CardFragmentListener)
        cardTextView = view.findViewById(R.id.card_text)
        buttonSuccess = view.findViewById(R.id.button_success)
        buttonSuccess.setOnClickListener { cardFragmentListener.onButtonSuccessClick(currentIndex) }
        buttonFail = view.findViewById(R.id.button__fail)
        buttonFail.setOnClickListener { cardFragmentListener.onButtonFailClick(currentIndex) }
        cardContainer = view.findViewById(R.id.card_container)
        cardContainer.setOnClickListener { changeText(card = card) }
        cardTextView.text = card.frontWord
        return view
    }


    companion object {

        fun createInstance(
            card: Card,
            currentIndex: Int
        ): CardFragment =
            CardFragment().apply {
                this.card = card
                this.currentIndex = currentIndex
                arguments = Bundle()
                    .also { args ->
                        // put in bundle info from activity
                    }
            }
    }

    fun updateFragment(position: Int, card: Card) {
        this.card = card
        this.currentIndex = position
        cardTextView.text = card.frontWord
    }

    private fun changeText(card: Card) {
        if (cardTextView.text == card.frontWord) {
            cardTextView.text = card.backWord
        } else {
            cardTextView.text = card.frontWord
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("CARD_KEY", card)
        outState.putInt("INDEX_KEY", currentIndex)

    }
}