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
import com.englishcard.ui.main.adapter.CardAdapter
import com.englishcard.ui.main.adapter.CardListener
import com.englishcard.ui.main.adapter.CardSetListener

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment(cardListener: CardListener) : Fragment() {

    private val cardAdapter = CardAdapter(emptyList(), cardListener)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val  view = inflater.inflate(R.layout.fragment_second, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_card)
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = cardAdapter
        return view
    }


    companion object {

        fun createInstance(cardListener: CardListener) : SecondFragment =
            SecondFragment(cardListener).apply {
                arguments = Bundle()
                    .also { args ->
                        // put in bundle info from activity
                    }
            }

    }
}