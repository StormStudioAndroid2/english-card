package com.englishcard.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewModelScope
import com.englishcard.model.database.EnglishCardDatabase
import com.englishcard.model.database.CardSetEntity
import com.englishcard.model.database.CardSetWithCards
import com.englishcard.model.domain.cards.CardSet
import com.englishcard.ui.base.BasePresenter
import com.englishcard.ui.main.adapter.CardListener
import com.englishcard.ui.main.adapter.CardSetListener
import kotlinx.coroutines.launch

class MainPresenter() : BasePresenter<MainView>(), CardSetListener, CardListener {

    private var currentCardSet: CardSet? = null

    private var cardSetList: List<CardSet>? = null

    override fun attachView(view: MainView, lifecycle: Lifecycle) {
        super.attachView(view, lifecycle)
        view.createCardSetRecyclerViewFragment()
        view.notifyCardSetAdapter()
    }


    override fun loadDatabase(applicationContext: Context) {
        viewModelScope.launch {
            try {
                cardSetList =
                    EnglishCardDatabase.getInstance(context = applicationContext).cardSetDao()
                        .getCardSetAndCards().map(CardSetWithCards::convertToDomain)
                updateCardSets()
            } catch (e: Exception) {
                Log.i("MainPresenter", "cannot load from database!")
            }
            // handler error
        }
    }

    fun onFloatingButtonClicked() {
        view?.createCardSetDialog()
    }

    fun onDialogClosed(applicationContext: Context, cardSetName: String, cardSetLanguage: String) {
        viewModelScope.launch {
            try {
                EnglishCardDatabase.getInstance(context = applicationContext).cardSetDao()
                    .insertAll(CardSetEntity(cardSetName, cardSetLanguage))
                loadDatabase(applicationContext)
            } catch (e: Exception) {
                Log.i("MainPresenter", "cannot load from database!")
            }
            // handler error
        }
    }

    private fun updateCardSets() {
        cardSetList?.let { cardSetList1 ->
            view?.updateCardSetRecyclerView(cardSetList1)
        }
    }

    override fun onCardSetClicked(adapterPosition: Int) {
//        cardSetEntityList.let { list  ->
//
//
//        }
        Log.i("TEST_CLICK", "click on card!")
    }

    override fun onCardClicked(adapterPosition: Int) {
        TODO("Not yet implemented")
    }
}