package com.englishcard.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.englishcard.R
import com.englishcard.model.domain.cards.Card
import com.englishcard.model.domain.cards.CardSet
import com.englishcard.ui.main.adapter.CardAdapter
import com.englishcard.ui.main.adapter.CardListener
import com.englishcard.ui.main.adapter.CardSetListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
interface SecondFragmentListener {

    fun onCardCreateFabClicked()

    fun onCheckKnowledgeFabClicked()
}
class SecondFragment() : Fragment() {

    private lateinit var cardAdapter: CardAdapter
    private lateinit var secondFragmentListener: SecondFragmentListener
    private lateinit var cardSet: CardSet

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        secondFragmentListener = (activity as SecondFragmentListener)
        savedInstanceState?.getParcelable<CardSet>("CARD_SET_KEY")?.let {
            cardSet = it
        }
        cardAdapter = CardAdapter(emptyList())
        cardAdapter.update(cardSet.cards)

        val  view = inflater.inflate(R.layout.fragment_second, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_card)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = cardAdapter

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            secondFragmentListener.onCardCreateFabClicked()
        }
        view.findViewById<FloatingActionButton>(R.id.fab1).setOnClickListener {
            secondFragmentListener.onCheckKnowledgeFabClicked()
        }

        return view
    }


    companion object {

        fun createInstance(cardSet: CardSet) : SecondFragment =
            SecondFragment().apply {
                this.cardSet = cardSet
                arguments = Bundle()
                    .also { args ->
                        // put in bundle info from activity
                    }
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("CARD_SET_KEY", cardSet)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getParcelable<CardSet>("CARD_SET_KEY")?.let {
            cardSet = it
        }
    }

    fun loadCardSet(cardSet: CardSet) {
        cardAdapter.update(cardSet.cards)
    }

    fun updateCard(position: Int) {
        cardSet.cards[position].isFrontShow = !cardSet.cards[position].isFrontShow
        cardAdapter.update(cardSet.cards)
    }
}