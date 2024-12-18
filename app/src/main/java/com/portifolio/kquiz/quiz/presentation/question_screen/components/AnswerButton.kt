package com.portifolio.kquiz.quiz.presentation.question_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portifolio.kquiz.R
import com.portifolio.kquiz.core.theme.KQuizTheme
import com.portifolio.kquiz.quiz.domain.model.Answer
import com.portifolio.kquiz.quiz.presentation.question_screen.QuestionScreenPreviewData

@Composable
fun AnswerButton(
    modifier: Modifier = Modifier,
    index: Int,
    answer: Answer,
    isAnyAnswerSelected: Boolean,
    onAnswerSelected: (Answer) -> Unit,
) {

    val rowBackground = if (answer.isSelected) {

        MaterialTheme.colorScheme.primaryContainer
    } else {

        Color.Transparent
    }

    val answerText = answer.description

    val answerTextSize = if (answerText.length < 25) {

        20.sp
    } else {

        16.sp
    }

    Card(
        modifier = modifier
            .fillMaxWidth(),
        enabled = !isAnyAnswerSelected,
        onClick = {
            onAnswerSelected(answer)
        }
    ) {

        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = 70.dp)
                .fillMaxWidth()
                .background(rowBackground),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (isAnyAnswerSelected && answer.isSelected) {

                Box(
                    modifier = Modifier.padding(10.dp)
                ) {

                    Icon(
                        modifier = Modifier
                            .size(55.dp),
                        imageVector = if (answer.isCorrect) {

                            Icons.Default.CheckCircle
                        } else {

                            ImageVector.vectorResource(
                                id = R.drawable.ic_cancel
                            )
                        },
                        contentDescription = stringResource(R.string.correct_answer),
                        tint = MaterialTheme.colorScheme.primary

                    )
                }

            } else {

                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topStartPercent = 20,
                                bottomStartPercent = 20
                            )
                        )
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .width(70.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = (index + 1).toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            if (isAnyAnswerSelected && answer.isSelected) {

                val text = if (answer.isCorrect) {

                    stringResource(R.string.correct)
                } else {

                    stringResource(R.string.wrong)
                }

                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp),
                    text = text,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

            } else {

                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp),
                    text = answerText,
                    fontSize = answerTextSize
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun DefaultAnswerButtonPreview(modifier: Modifier = Modifier) {

    KQuizTheme {

        AnswerButton(
            index = 0,
            answer = QuestionScreenPreviewData.multipleChoiceAnswers[1],
            isAnyAnswerSelected = false,
            onAnswerSelected = {}
        )
    }
}

@PreviewLightDark
@Composable
fun SelectedRightAnswerButtonPreview(modifier: Modifier = Modifier) {

    KQuizTheme {

        AnswerButton(
            index = 0,
            answer = QuestionScreenPreviewData.multipleChoiceAnswers[2],
            isAnyAnswerSelected = true,
            onAnswerSelected = {}
        )
    }
}

@PreviewLightDark
@Composable
fun SelectedFalseAnswerButtonPreview(modifier: Modifier = Modifier) {

    KQuizTheme {

        AnswerButton(
            index = 0,
            answer = QuestionScreenPreviewData.multipleChoiceAnswers[1],
            isAnyAnswerSelected = true,
            onAnswerSelected = {}
        )
    }
}