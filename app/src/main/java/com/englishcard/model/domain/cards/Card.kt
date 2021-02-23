package com.englishcard.model.domain.cards

import com.englishcard.model.database.CardEntity

class Card(private val cardEntity: CardEntity) {

     var translateWord = cardEntity.translateWord
     var originalWord = cardEntity.originalWord
     var cardSetId = cardEntity.cardSetId
     var cardId = cardEntity.cardId

    fun convertToEntity(): CardEntity =
        CardEntity(cardSetId, originalWord, translateWord).also { cardEntity ->
            cardEntity.cardId = cardId
        }

}