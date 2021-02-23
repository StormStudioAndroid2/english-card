package com.englishcard.model.database

import androidx.room.*


@Dao
interface CardSetDao {

    @Query("SELECT * FROM card_set")
    suspend fun getAll(): List<CardSetEntity>

    @Delete
    suspend fun delete(cardSetEntity: CardSetEntity)

    @Insert
    suspend fun insertAll(vararg cardSetEntity: CardSetEntity)

    @Update
    fun update(cardSetEntity: CardSetEntity)

    @Transaction
    @Query("SELECT * FROM card WHERE cardSetId IN (SELECT DISTINCT(cardSetId) FROM card_set)")
    suspend fun getCardSetAndCards(): List<CardSetWithCards>
}