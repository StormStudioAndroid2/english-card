package com.englishcard.ui.main

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewModelScope
import com.englishcard.model.EnglishCardDatabase
import com.englishcard.model.cardset.CardSetEntity
import com.englishcard.ui.base.BasePresenter
import kotlinx.coroutines.launch

class MainPresenter() : BasePresenter<MainView>() {


    private var data: List<CardSetEntity>? = null
    override fun attachView(view: MainView, lifecycle: Lifecycle) {
        super.attachView(view, lifecycle)
        view.createCardSetRecyclerViewFragment()
        view.notifyCardSetAdapter()
    }


    override fun loadDatabase(applicationContext: Context) {
        viewModelScope.launch {
            try {
                data = EnglishCardDatabase.getInstance(context = applicationContext).cardSetDao().getAll()
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
                EnglishCardDatabase.getInstance(context = applicationContext).cardSetDao().insertAll(CardSetEntity(cardSetName, cardSetLanguage))
                loadDatabase(applicationContext)
            } catch (e: Exception) {
                Log.i("MainPresenter", "cannot load from database!")
            }
            // handler error
        }
    }

    private fun updateCardSets() {
        data?.let { cardSetList ->
            view?.updateCardSetRecyclerView(cardSetList)
        }
    }
}