package com.portifolio.kquiz.quiz.presentation.question_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portifolio.kquiz.R
import com.portifolio.kquiz.core.theme.KQuizTheme
import com.portifolio.kquiz.quiz.domain.model.Answer
import com.portifolio.kquiz.quiz.domain.model.Question
import com.portifolio.kquiz.quiz.presentation.question_screen.QuestionScreenPreviewData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnsweringBox(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    timeLeft: Int,
    question: Question,
    onAnswerSelected: (Answer) -> Unit
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(20.dp)
    ) {

        item {

            Row(
                modifier = Modifier
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Icon(
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colorScheme.onSurface,
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.ic_timer
                    ),
                    contentDescription = stringResource(R.string.timer),
                )

                Spacer(modifier = Modifier.padding(horizontal = 5.dp))

                Text(
                    text = "${timeLeft}s",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 20.dp),
                thickness = 2.dp
            )

            Text(
                text = question.description,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                lineHeight = 30.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = modifier.padding(horizontal = 5.dp)
            )

            Spacer(modifier = Modifier.padding(vertical = 10.dp))
        }

        items(question.answers.size) { index ->

            AnswerButton(
                index = index,
                answer = question.answers[index],
                isAnyAnswerSelected = question.wasAnswered,
                onAnswerSelected = onAnswerSelected
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
fun DefaultAnsweringBoxPreview(modifier: Modifier = Modifier) {

    KQuizTheme {

        AnsweringBox(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
            timeLeft = 10,
            question = QuestionScreenPreviewData.multipleAnswerQuestion,
            onAnswerSelected = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
fun SelectedAnsweringBoxPreview(modifier: Modifier = Modifier) {

    KQuizTheme {

        AnsweringBox(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
            timeLeft = 10,
            question = QuestionScreenPreviewData.multipleAnswerQuestion,
            onAnswerSelected = {}
        )
    }
}