package com.englishcard.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CardSetEntity::class, CardEntity::class), version = 1)
abstract class EnglishCardDatabase : RoomDatabase() {

    abstract fun cardSetDao(): CardSetDao

    companion object {

        @Volatile
        private var INSTANCE: EnglishCardDatabase? = null

        fun getInstance(context: Context): EnglishCardDatabase {
            synchronized(this) {
                var instance =
                    INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EnglishCardDatabase::class.java,
                        "english_card_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

