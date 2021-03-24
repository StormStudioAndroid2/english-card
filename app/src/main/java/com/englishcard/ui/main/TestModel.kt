package com.englishcard.ui.main

import android.os.Parcelable
import com.englishcard.model.domain.cards.Card
import kotlinx.android.parcel.Parcelize

@Parcelize
class TestModel(
    val cardSetName: String,
    val cardList: List<Card>,
    var rightAnswers: Int,
    var wrongAnswers: Int
) : Parcelable