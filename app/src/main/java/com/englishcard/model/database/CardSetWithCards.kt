package com.englishcard.model.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.englishcard.model.domain.cards.Card
import com.englishcard.model.domain.cards.CardSet

@Entity(primaryKeys = ["cardSetId", "cardId"])
data class CardSetWithCards(
    @Embedded val cardSetEntity: CardSetEntity,
    @Relation(
        parentColumn = "cardSetId",
        entityColumn = "cardId"
    )
    val cards: List<CardEntity>
) {

    fun convertToDomain(): CardSet = CardSet(cardSetEntity, cards.map(CardEntity::convertToDomain))
}