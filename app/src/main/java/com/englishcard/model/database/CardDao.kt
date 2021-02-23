package com.englishcard.model.database

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update

interface CardDao {
    @Query("SELECT * FROM card")
    suspend fun getAll(): List<CardEntity>

    @Delete
    suspend fun delete(cardSetEntity: CardEntity)

    @Update
    suspend fun update(cardEntity: CardEntity)
}