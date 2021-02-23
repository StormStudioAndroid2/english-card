package com.englishcard.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.englishcard.model.domain.cards.Card

@Entity(tableName = "card")
data class CardEntity(
    var cardSetId: Long,
    @ColumnInfo(name = "card_original_word") var originalWord: String,
    @ColumnInfo(name = "card_translate_word") var translateWord: String
) {
    @PrimaryKey(autoGenerate = true) var cardId: Long = 0

    fun convertToDomain() : Card = Card(this)
}