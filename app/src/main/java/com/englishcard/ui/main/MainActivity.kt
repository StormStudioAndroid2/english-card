package com.englishcard.ui.main

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.commit
import com.englishcard.R
import com.englishcard.model.database.CardSetEntity
import com.englishcard.model.domain.cards.CardSet


interface MainView {

    fun addSet()

    fun createCardSetRecyclerViewFragment()

    fun createCardRecyclerViewFragment()

    fun createCardSetDialog()

    fun notifyCardSetAdapter()

    fun updateCardSetRecyclerView(cardSets: List<CardSet>)

    interface DialogCallback {

        fun onDialogYes(cardSetName : String, cardSetLanguage: String)
    }

}

class MainActivity : AppCompatActivity(), MainView, MainView.DialogCallback {

    private val mainPresenter = MainPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        mainPresenter.attachView(this, this.lifecycle)
        mainPresenter.loadDatabase(applicationContext)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            mainPresenter.onFloatingButtonClicked()
        }
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

    override fun createCardSetRecyclerViewFragment() {
        supportFragmentManager.commit {
            replace(
                R.id.main_container_fragment,
                FirstFragment.createInstance(mainPresenter), "CARD_SET_FRAGMENT"
            )
        }
    }

    override fun createCardRecyclerViewFragment() {
        supportFragmentManager.commit {
            replace(
                R.id.main_container_fragment,
                SecondFragment.createInstance(mainPresenter), "CARD_SET_FRAGMENT"
            )
        }
    }

    override fun createCardSetDialog() {
        MainCardSetDialog().show(supportFragmentManager, "DIALOG_CARD_SET_FRAGMENT")
    }

    override fun notifyCardSetAdapter() {
        (supportFragmentManager.findFragmentByTag("CARD_SET_FRAGMENT") as? FirstFragment)?.notifyAdapter()
    }

    override fun updateCardSetRecyclerView(cardSets: List<CardSet>) {
        (supportFragmentManager.findFragmentByTag("CARD_SET_FRAGMENT") as? FirstFragment)?.updateRecyclerView(cardSets)

    }

    override fun onDialogYes(cardSetName: String, cardSetLanguage: String) {
      mainPresenter.onDialogClosed(applicationContext, cardSetName, cardSetLanguage)
    }
}