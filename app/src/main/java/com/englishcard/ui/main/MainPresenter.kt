package com.englishcard.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewModelScope
import com.englishcard.model.database.CardEntity
import com.englishcard.model.database.EnglishCardDatabase
import com.englishcard.model.database.CardSetEntity
import com.englishcard.model.database.CardSetWithCards
import com.englishcard.model.domain.cards.Card
import com.englishcard.model.domain.cards.CardSet
import com.englishcard.ui.base.BasePresenter
import com.englishcard.ui.main.adapter.CardListener
import com.englishcard.ui.main.adapter.CardSetListener
import kotlinx.coroutines.launch
import java.util.*

class MainPresenter() : BasePresenter<MainView>() {

    private var currentCardSet: CardSet? = null

    private var cardSetList: List<CardSet>? = null

    private var currentTest: TestModel? = null

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

    fun onFabCreateCardSetClicked() {
        if (currentCardSet == null) {
            view?.createCardSetDialog()
        }
    }

    fun onCardSetClicked(adapterPosition: Int) {
        cardSetList?.get(adapterPosition)?.let { cardSet ->
            openCardRecyclerViewFragment(cardSet)
        }
    }

    fun onCardClicked(adapterPosition: Int) {
        view?.updateCardInList(adapterPosition)
    }

    fun onCardCreateFabClicked() {
        view?.createCardDialog()
    }


    fun onCheckKnowledgeFabClicked() {
        checkKnowledge()
    }

    fun openCardRecyclerViewFragment(cardSet: CardSet) {
        currentCardSet = cardSet
        view?.createCardRecyclerViewFragment(cardSet)
        view?.updateCardRecyclerView(cardSet)
    }

    fun onDialogCardSetClosed(
        applicationContext: Context,
        cardSetName: String,
        cardSetLanguage: String
    ) {
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

    fun onDialogCardClosed(applicationContext: Context, frontWord: String, backWord: String) {
        viewModelScope.launch {
            try {
                currentCardSet?.let { cardSet ->
                    EnglishCardDatabase.getInstance(context = applicationContext).cardDao()
                        .insertAll(CardEntity(cardSet.cardSetId, frontWord, backWord))
                    currentCardSet =
                        EnglishCardDatabase.getInstance(applicationContext).cardSetDao()
                            .getCardSetAndCards()
                            .findLast { it.cardSetEntity.cardSetId == cardSet.cardSetId }
                            ?.convertToDomain()
                }
                currentCardSet?.let { cardSet ->
                    view?.updateCardRecyclerView(cardSet)
                }
            } catch (e: Exception) {
                Log.i("MainPresenter", "cannot update database!")
            }
            // handler error
        }
    }

    fun onBackPressed(context: Context) {
        if (currentTest != null && currentCardSet != null) {
            currentTest = null
            currentCardSet?.let { cardSet ->
                openCardRecyclerViewFragment(cardSet)
            }
        } else if (currentCardSet != null) {
            openCardSetFragment(context)
        } else {
            view?.close()
        }
    }

    fun onButtonSuccessClick(position: Int) {
        val newPosition = position+1;
        currentTest?.let { test ->
            test.rightAnswers++
            if (newPosition != test.cardList.size ) {
                view?.updateCardFragment(test.cardList[newPosition], newPosition)
            } else {
                currentCardSet?.let { cardSet ->
                    currentTest?.let {testModel ->
                        view?.createTestResultFragment(testModel)
                    }
                }
            }
        }
    }

    fun onButtonFailClick(position: Int) {
        val newPosition = position+1;
        currentTest?.let { test ->
            test.wrongAnswers++
            if (newPosition != test.cardList.size ) {
                view?.updateCardFragment(test.cardList[newPosition], newPosition)
            } else {
                currentCardSet?.let { cardSet ->
                    currentTest?.let {testModel ->
                        view?.createTestResultFragment(testModel)
                    }
                }
            }
        }
    }

    fun onCompleteButtonClick(context: Context) {
        currentTest = null
        currentCardSet?.let {
            view?.createCardRecyclerViewFragment(it)
        } ?:openCardSetFragment(context)
    }

    private fun updateCardSets() {
        cardSetList?.let { cardSetList1 ->
            view?.updateCardSetRecyclerView(cardSetList1)
        }
    }

    private fun checkKnowledge() {
        val name = currentCardSet?.name ?: ""
        currentCardSet?.cards?.shuffled()?.let { cards ->
            currentTest = TestModel(name, cards, 0, 0)
            openCard(0, cards.first())
        }
    }

    private fun openCard(position: Int, card: Card) {
        if (position == 0) {
            view?.createCardFragment(card, position)
        } else {
            view?.updateCardFragment(card, position)
        }
    }

    private fun openCardSetFragment(context: Context) {
        currentCardSet = null
        cardSetList?.let {
            view?.createCardSetRecyclerViewFragment(it)
        } ?: view?.createCardSetRecyclerViewFragment(emptyList())
        loadDatabase(context)
    }
}