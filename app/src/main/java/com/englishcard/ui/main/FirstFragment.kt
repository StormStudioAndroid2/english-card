package com.englishcard.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.englishcard.R
import com.englishcard.model.domain.cards.CardSet
import com.englishcard.ui.main.adapter.CardSetAdapter
import com.englishcard.ui.main.adapter.CardSetListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

interface FirstFragmentListener {
    fun onFabCreateCardSetClicked()
}
class FirstFragment() : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var firstFragmentListener: FirstFragmentListener
    private lateinit var cardSetListener: CardSetListener
    private lateinit var cardSets: List<CardSet>

    private lateinit var adapter : CardSetAdapter

    companion object {

        fun createInstance(cardSets: List<CardSet>): FirstFragment =
            FirstFragment().apply {
                this.cardSets = cardSets
                arguments = Bundle()
                    .also { args ->
                        // put in bundle info from activity
                    }
            }

    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_first, container, false)
        savedInstanceState?.getParcelableArrayList<CardSet>("CARD_SETS_KEY")?.let { arrayList ->
            cardSets = arrayList.toList()
        }

        recyclerView = view.findViewById(R.id.recycler_view_card_set)
        cardSetListener = (activity as CardSetListener)
        adapter = CardSetAdapter(emptyList(), cardSetListener)
        recyclerView.adapter = adapter
        adapter.update(cardSets)
        recyclerView.layoutManager = LinearLayoutManager(context)

        firstFragmentListener = (activity as FirstFragmentListener)
        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            firstFragmentListener.onFabCreateCardSetClicked()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun updateRecyclerView(cardSets: List<CardSet>) {
        adapter.update(cardSets)
        this.cardSets = cardSets
    }

    fun notifyAdapter() {
        adapter.notifyDataSetChanged()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("CARD_SETS_KEY", ArrayList(cardSets))
    }

}