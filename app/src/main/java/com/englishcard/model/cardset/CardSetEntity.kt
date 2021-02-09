package com.englishcard.model.cardset

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "card_set")
data class CardSetEntity(
        @ColumnInfo(name = "card_set_name") var name: String?,
        @ColumnInfo(name = "card_set_language") var language: String?
) {
        @PrimaryKey(autoGenerate = true) var uid: Int = 0

}