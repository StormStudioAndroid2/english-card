package com.englishcard.model.domain.cards

import com.englishcard.model.database.CardSetEntity

class CardSet(private val cardSetEntity: CardSetEntity, var cards: List<Card>) {

     var name = cardSetEntity.name

     var language = cardSetEntity.language

     var cardSetId = cardSetEntity.cardSetId

    fun convertToEntity(): CardSetEntity {
        return CardSetEntity(name, language).also { cardSetEntity ->
            cardSetEntity.cardSetId = cardSetId
        }
    }
}