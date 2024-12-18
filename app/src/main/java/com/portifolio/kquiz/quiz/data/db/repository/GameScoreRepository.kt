package com.portifolio.kquiz.quiz.data.db.repository

import com.portifolio.kquiz.quiz.data.core.toGameScore
import com.portifolio.kquiz.quiz.data.core.toGameScoreDto
import com.portifolio.kquiz.quiz.data.db.repository.dao.GameScoreDao
import com.portifolio.kquiz.quiz.domain.model.GameScore

class GameScoreRepository(
    private val gameScoreDao: GameScoreDao
) {

    suspend fun insertGameScore(gameScore: GameScore) = gameScoreDao.insertGameScore(gameScore.toGameScoreDto())

    suspend fun getRecentGameScores(count: Int): List<GameScore> {

        return gameScoreDao.getRecentGameScores(count).map { it.toGameScore() }
    }
}

