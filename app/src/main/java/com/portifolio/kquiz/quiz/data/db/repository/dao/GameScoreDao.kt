package com.portifolio.kquiz.quiz.data.db.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.portifolio.kquiz.quiz.data.db.model.GameScoreDto

@Dao
interface GameScoreDao {

    @Insert
    suspend fun insertGameScore(gameScore: GameScoreDto)

    @Query("SELECT * FROM game_score ORDER BY id DESC LIMIT :count")
    suspend fun getRecentGameScores(count: Int): List<GameScoreDto>
}