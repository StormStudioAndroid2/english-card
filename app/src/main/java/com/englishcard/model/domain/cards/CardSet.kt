package com.englishcard.model.domain.cards

import android.os.Parcelable
import com.englishcard.model.database.CardSetEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
class CardSet(val name: String, val language: String, val cardSetId: Long, var cards: List<Card>) : Parcelable {

    fun convertToEntity(): CardSetEntity {
        return CardSetEntity(name, language).also { cardSetEntity ->
            cardSetEntity.cardSetId = cardSetId
        }
    }
}