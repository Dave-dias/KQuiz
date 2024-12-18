package com.portifolio.kquiz.quiz.presentation.question_screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.portifolio.kquiz.R
import com.portifolio.kquiz.core.theme.KQuizTheme
import com.portifolio.kquiz.quiz.presentation.question_screen.QuestionScreenPreviewData
import com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel.QuestionScreenIntent
import com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel.QuestionScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreenTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    state: QuestionScreenState,
    onIntent: (QuestionScreenIntent) -> Unit
) {

    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {

            Text(
                text = stringResource(
                    R.string.score_x_slash_y,
                    state.correctAnswers,
                    state.totalOfQuestions
                ),
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {

            IconButton(
                onClick = {
                    onIntent(QuestionScreenIntent.NavigateBack)
                }
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.go_back)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
fun DefaultQuestionScreenAppBarPreview(modifier: Modifier = Modifier) {

    KQuizTheme {

        QuestionScreenTopAppBar(
            state = QuestionScreenPreviewData.defaultQuestionScreenState,
            onIntent = {}
        )
    }
}