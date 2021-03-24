package com.englishcard.model.database

import androidx.room.*

@Dao
interface CardDao {
    @Query("SELECT * FROM card")
    suspend fun getAll(): List<CardEntity>

    @Delete
    suspend fun delete(cardSetEntity: CardEntity)

    @Update
    suspend fun update(cardEntity: CardEntity)

    @Insert
    suspend fun insertAll(vararg cardEntity: CardEntity)
}