package com.englishcard.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.englishcard.model.domain.cards.Card

@Entity(tableName = "card")
data class CardEntity(
    var cardSetOwnerId: Long,
    @ColumnInfo(name = "card_front_word") var frontWord: String,
    @ColumnInfo(name = "card_back_word") var backWord: String
) {
    @PrimaryKey(autoGenerate = true)
    var cardId: Long = 0

    fun convertToDomain(): Card = Card(
        frontWord = this.frontWord,
        backWord = this.backWord,
        cardSetOwnerId = this.cardSetOwnerId,
        cardId = this.cardId
    )
}