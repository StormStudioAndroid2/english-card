package com.englishcard.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.englishcard.R
import com.englishcard.model.cardset.CardSetEntity
import com.englishcard.ui.main.adapter.CardSetAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = CardSetAdapter(emptyList())

    companion object {

        fun createInstance() : FirstFragment =
            FirstFragment().apply {
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
        var view =  inflater.inflate(R.layout.fragment_first, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_card_set)
        recyclerView.adapter = adapter
        recyclerView.setLayoutManager(LinearLayoutManager(context))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun updateRecyclerView(cardSets: List<CardSetEntity>) {
        adapter.update(cardSets)
    }

    fun notifyAdapter() {
        adapter.notifyDataSetChanged()
    }
}