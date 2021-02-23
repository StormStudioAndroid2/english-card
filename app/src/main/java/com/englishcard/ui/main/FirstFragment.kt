package com.englishcard.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.englishcard.R
import com.englishcard.model.database.CardSetEntity
import com.englishcard.model.domain.cards.CardSet
import com.englishcard.ui.main.adapter.CardSetAdapter
import com.englishcard.ui.main.adapter.CardSetListener

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment(cardSetListener: CardSetListener) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = CardSetAdapter(emptyList(), cardSetListener)

    companion object {

        fun createInstance(cardSetListener: CardSetListener) : FirstFragment =
            FirstFragment(cardSetListener).apply {
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
        recyclerView = view.findViewById(R.id.recycler_view_card_set)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun updateRecyclerView(cardSets: List<CardSet>) {
        adapter.update(cardSets)
    }

    fun notifyAdapter() {
        adapter.notifyDataSetChanged()
    }
}