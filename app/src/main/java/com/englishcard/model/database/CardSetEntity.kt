package com.englishcard.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.englishcard.model.domain.cards.Card
import com.englishcard.model.domain.cards.CardSet


@Entity(tableName = "card_set")
data class CardSetEntity(
        @ColumnInfo(name = "card_set_name") var name: String,
        @ColumnInfo(name = "card_set_language") var language: String
) {
        @PrimaryKey(autoGenerate = true) var cardSetId: Long = 0

        fun convertToDomain(): CardSet = CardSet(this, mutableListOf())
}