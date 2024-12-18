package com.portifolio.kquiz.quiz.presentation.question_screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.portifolio.kquiz.R
import com.portifolio.kquiz.core.theme.KQuizTheme
import com.portifolio.kquiz.quiz.presentation.core.components.BottomBarWithButton
import com.portifolio.kquiz.quiz.presentation.question_screen.QuestionScreenPreviewData
import com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel.QuestionScreenIntent
import com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel.QuestionScreenState

@Composable
fun QuestionScreenBottomAppBar(
    state: QuestionScreenState,
    onIntent: (QuestionScreenIntent) -> Unit
) {

    when {

        state.isError || state.isLoading -> {

            BottomBarWithButton(
                textButton = stringResource(R.string.retry),
                isButtonEnabled = state.isError,
                iconButton = {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(R.string.try_to_load_data_again)
                    )
                },
                onClick = {
                    onIntent(QuestionScreenIntent.RetryLoading)
                }
            )
        }

        state.isFinished -> {

            BottomBarWithButton(
                textButton = stringResource(R.string.finish),
                iconButton = {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = stringResource(R.string.finish_game)
                    )
                },
                onClick = {
                    onIntent(QuestionScreenIntent.FinishGame)
                }
            )
        }

        else -> {

            BottomBarWithButton(
                textButton = stringResource(R.string.next),
                isButtonEnabled = state.question?.wasAnswered ?: false,
                iconButton = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.next)
                    )
                },
                onClick = {
                    onIntent(QuestionScreenIntent.NextQuestion)
                }
            )
        }
    }
}

@PreviewLightDark
@Composable
fun LoadingQuestionScreenBottomBarPreview() {

    KQuizTheme {

        QuestionScreenBottomAppBar(
            state = QuestionScreenPreviewData.loadingQuestionScreenState,
            onIntent = {}
        )
    }
}

@PreviewLightDark
@Composable
fun ErrorQuestionScreenPreview(modifier: Modifier = Modifier) {

    KQuizTheme {

        QuestionScreenBottomAppBar(
            state = QuestionScreenPreviewData.errorQuestionScreenState,
            onIntent = {}
        )
    }
}

@PreviewLightDark
@Composable
fun DefaultQuestionScreenBottomBarPreview() {

    KQuizTheme {

        QuestionScreenBottomAppBar(
            state = QuestionScreenPreviewData.defaultQuestionScreenState,
            onIntent = {}
        )
    }
}