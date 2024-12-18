package com.portifolio.kquiz.quiz.domain.use_case

import com.portifolio.kquiz.quiz.data.db.repository.GameScoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRecentGameScoresUseCase @Inject constructor(
    private val repository: GameScoreRepository
) {

    suspend operator fun invoke(count: Int) = withContext(Dispatchers.IO) {

       repository.getRecentGameScores(count)
    }
}