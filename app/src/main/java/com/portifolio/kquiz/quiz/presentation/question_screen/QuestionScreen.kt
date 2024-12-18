package com.portifolio.kquiz.quiz.presentation.question_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.portifolio.kquiz.R
import com.portifolio.kquiz.core.theme.KQuizTheme
import com.portifolio.kquiz.quiz.presentation.question_screen.components.AnsweringBox
import com.portifolio.kquiz.quiz.presentation.question_screen.components.QuestionScreenTopAppBar
import com.portifolio.kquiz.quiz.presentation.question_screen.components.QuestionScreenBottomAppBar
import com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel.QuestionScreenEffect
import com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel.QuestionScreenIntent
import com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel.QuestionScreenState
import com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel.QuestionScreenViewModel

@Composable
fun QuestionScreenRoot(
    viewModel: QuestionScreenViewModel,
    navController: NavController
) {

    val state = viewModel.uiState.collectAsState()
    val effect = viewModel.effect

    LaunchedEffect(effect) {

        effect.collect { effect ->

            if (effect is QuestionScreenEffect.NavigateBack) {

                navController.popBackStack()
            }
        }
    }

    QuestionScreen(
        state = state.value,
        onIntent = { intent ->
            viewModel.processIntent(intent)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(
    state: QuestionScreenState,
    onIntent: (QuestionScreenIntent) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {

            QuestionScreenTopAppBar(
                scrollBehavior = scrollBehavior,
                state = state,
                onIntent = onIntent
            )
        },
        bottomBar = {

            QuestionScreenBottomAppBar(state, onIntent)
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {

            when {
                state.isLoading -> CircularProgressIndicator()
                state.isError -> MessageDisplay(state.errorMessage)
                state.isFinished -> MessageDisplay(
                    stringResource(R.string.you_ve_finished_all_questions_well_done)
                )
                state.question != null -> {
                    AnsweringBox(
                        scrollBehavior = scrollBehavior,
                        timeLeft = state.timeLeft,
                        question = state.question,

                        onAnswerSelected = {
                            onIntent(
                                QuestionScreenIntent.SelectAnswer(
                                    answer = it
                                )
                            )
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun MessageDisplay(message: String) {

    val messageTextSize = if (message.length < 10) {

        45.sp
    } else if (message.length < 30) {

        40.sp
    } else {

        30.sp
    }

    Text(
        text = message,
        fontWeight = FontWeight.Bold,
        fontSize = messageTextSize,
        lineHeight = messageTextSize,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}

@PreviewLightDark
@Composable
fun LoadingQuestionScreenPreview(modifier: Modifier = Modifier) {

    KQuizTheme {

        QuestionScreen(
            state = QuestionScreenPreviewData.loadingQuestionScreenState,
            onIntent = {}
        )
    }
}

@PreviewLightDark
@Composable
fun ErrorQuestionScreenPreview(modifier: Modifier = Modifier) {

    KQuizTheme {

        QuestionScreen(
            state = QuestionScreenPreviewData.errorQuestionScreenState,
            onIntent = {}
        )
    }
}

@PreviewLightDark
@Composable
fun DefaultQuestionScreenPreview(modifier: Modifier = Modifier) {

    KQuizTheme {

        QuestionScreen(
            state = QuestionScreenPreviewData.defaultQuestionScreenState,
            onIntent = {}
        )
    }
}