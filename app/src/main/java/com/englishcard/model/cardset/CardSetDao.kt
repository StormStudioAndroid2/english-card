package com.englishcard.model.cardset

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CardSetDao {

    @Query("SELECT * FROM card_set")
    suspend fun getAll(): List<CardSetEntity>

    @Delete
    suspend fun delete(cardSetEntity: CardSetEntity)

    @Insert
    suspend fun insertAll(vararg cardSetEntity: CardSetEntity)
}