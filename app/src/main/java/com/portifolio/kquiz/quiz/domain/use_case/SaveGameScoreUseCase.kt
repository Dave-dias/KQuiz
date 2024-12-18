package com.portifolio.kquiz.quiz.domain.use_case

import com.portifolio.kquiz.quiz.data.db.repository.GameScoreRepository
import com.portifolio.kquiz.quiz.domain.model.GameScore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveGameScoreUseCase @Inject constructor(
    private val gameScoreRepository: GameScoreRepository
){

    suspend operator fun invoke(gameScore: GameScore) = withContext(Dispatchers.IO) {

        gameScoreRepository.insertGameScore(gameScore)
    }
}
