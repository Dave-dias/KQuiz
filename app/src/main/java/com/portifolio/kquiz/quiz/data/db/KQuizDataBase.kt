package com.portifolio.kquiz.quiz.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.portifolio.kquiz.quiz.data.db.model.GameScoreDto
import com.portifolio.kquiz.quiz.data.db.repository.dao.GameScoreDao

private const val DATABASE_NAME = "kquiz_database.db"

@Database(entities = [GameScoreDto::class], version = 1)
abstract class KQuizDataBase : RoomDatabase() {

       abstract fun gameScoreDao(): GameScoreDao

    companion object {

        fun buildDatabase(context: Context): KQuizDataBase {

            return Room.databaseBuilder(
                context = context,
                klass = KQuizDataBase::class.java,
                name = DATABASE_NAME
            ).build()

        }
    }
}