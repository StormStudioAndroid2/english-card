package com.englishcard.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.englishcard.R
import com.englishcard.model.domain.cards.Card
import com.englishcard.model.domain.cards.CardSet
import com.englishcard.ui.main.adapter.CardListener
import com.englishcard.ui.main.adapter.CardSetListener


interface MainView {

    fun addSet()

    fun createCardSetRecyclerViewFragment(cardSets: List<CardSet>)

    fun createCardRecyclerViewFragment(cardSet: CardSet)

    fun createCardSetDialog()

    fun createCardDialog()

    fun notifyCardSetAdapter()

    fun notifyCardAdapter()

    fun updateCardSetRecyclerView(cardSets: List<CardSet>)

    fun updateCardRecyclerView(cardSet: CardSet)

    fun createCardFragment(card: Card, position: Int)

    fun updateCardFragment(card: Card, position: Int)

    fun updateCardInList(position: Int)

    fun close()

    fun createTestResultFragment(testModel: TestModel)

    interface DialogCallback {

        fun onDialogCardSetYes(cardSetName : String, cardSetLanguage: String)

        fun onDialogCardYes(frontWord: String, backWord: String)
    }

}

class MainActivity : AppCompatActivity(),
    MainView,
    MainView.DialogCallback,
    SecondFragmentListener,
    CardListener,
    FirstFragmentListener,
    CardSetListener,
    CardFragmentListener,
    TestResultFragmentListener {

    private val mainPresenter : MainPresenter by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        mainPresenter.attachView(this, this.lifecycle)
        if (savedInstanceState == null) {
            createCardSetRecyclerViewFragment(emptyList())
            mainPresenter.loadDatabase(applicationContext)
        }
    }

    override fun onBackPressed() {
        mainPresenter.onBackPressed(applicationContext)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun addSet() {
        TODO("Not yet implemented")
    }

    override fun createCardSetRecyclerViewFragment(cardSets: List<CardSet>) {
        supportFragmentManager.commit {
            replace(
                R.id.main_container_fragment,
                FirstFragment.createInstance(cardSets), "CARD_SET_LIST_FRAGMENT"
            )
        }
    }

    override fun createCardRecyclerViewFragment(cardSet: CardSet) {
        supportFragmentManager.commit {
            replace(
                R.id.main_container_fragment,
                SecondFragment.createInstance(cardSet), "CARD_SET_FRAGMENT"
            )
        }
    }

    override fun createCardSetDialog() {
        MainCardSetDialog().show(supportFragmentManager, "DIALOG_CARD_SET_FRAGMENT")
    }

    override fun createCardDialog() {
        MainCardDialog().show(supportFragmentManager, "DIALOG_CARD_FRAGMENT")
    }

    override fun notifyCardSetAdapter() {
        (supportFragmentManager.findFragmentByTag("CARD_SET_LIST_FRAGMENT") as? FirstFragment)?.notifyAdapter()
    }

    override fun notifyCardAdapter() {

    }

    override fun updateCardSetRecyclerView(cardSets: List<CardSet>) {
        (supportFragmentManager.findFragmentByTag("CARD_SET_LIST_FRAGMENT") as? FirstFragment)?.updateRecyclerView(cardSets)

    }

    override fun updateCardRecyclerView(cardSet: CardSet) {
        (supportFragmentManager.findFragmentByTag("CARD_SET_FRAGMENT") as? SecondFragment)?.loadCardSet(cardSet)
    }

    override fun createCardFragment(card: Card, position: Int) {
        supportFragmentManager.commit {
            replace(
                R.id.main_container_fragment,
                CardFragment.createInstance(card, position), "CARD_FRAGMENT")
        }
    }

    override fun updateCardFragment(card: Card, position: Int) {
        (supportFragmentManager.findFragmentByTag("CARD_FRAGMENT") as? CardFragment)?.updateFragment(position, card)
    }

    override fun updateCardInList(position: Int) {
        (supportFragmentManager.findFragmentByTag("CARD_SET_FRAGMENT") as? SecondFragment)?.updateCard(position)
    }

    override fun close() {
        finish()
    }

    override fun createTestResultFragment(testModel: TestModel) {
        supportFragmentManager.commit {
            replace(
                R.id.main_container_fragment,
                TestResultFragment.createInstance(testModel), "RESULT_FRAGMENT"
            )
        }    }

    override fun onCardCreateFabClicked() {
        mainPresenter.onCardCreateFabClicked()
    }

    override fun onCheckKnowledgeFabClicked() {
        mainPresenter.onCheckKnowledgeFabClicked()
    }

    override fun onDialogCardSetYes(cardSetName: String, cardSetLanguage: String) {
      mainPresenter.onDialogCardSetClosed(applicationContext, cardSetName, cardSetLanguage)
    }

    override fun onDialogCardYes(frontWord: String, backWord: String) {
        mainPresenter.onDialogCardClosed(applicationContext, frontWord, backWord)
    }

    override fun onCardClicked(adapterPosition: Int) {
        mainPresenter.onCardClicked(adapterPosition)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onFabCreateCardSetClicked() {
        mainPresenter.onFabCreateCardSetClicked()
    }

    override fun onCardSetClicked(adapterPosition: Int) {
        mainPresenter.onCardSetClicked(adapterPosition)
    }

    override fun onButtonSuccessClick(position: Int) {
        mainPresenter.onButtonSuccessClick(position)
    }

    override fun onButtonFailClick(position: Int) {
        mainPresenter.onButtonFailClick(position)
    }

    override fun onCompleteButtonClick() {
        mainPresenter.onCompleteButtonClick(applicationContext)
    }
}