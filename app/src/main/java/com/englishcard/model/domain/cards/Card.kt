package com.englishcard.model.domain.cards

import android.os.Parcelable
import com.englishcard.model.database.CardEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
class Card(val frontWord: String, val backWord: String, val cardSetOwnerId: Long, val cardId: Long): Parcelable {

    var isFrontShow = true
    fun convertToEntity(): CardEntity =
        CardEntity(cardSetOwnerId, frontWord, backWord).also { cardEntity ->
            cardEntity.cardId = cardId
        }

}