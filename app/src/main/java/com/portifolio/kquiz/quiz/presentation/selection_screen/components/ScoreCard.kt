package com.portifolio.kquiz.quiz.presentation.selection_screen.components

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portifolio.kquiz.core.theme.KQuizTheme
import com.portifolio.kquiz.quiz.domain.model.GameScore
import com.portifolio.kquiz.quiz.presentation.selection_screen.SelectionScreenPreviewData

@Composable
fun ScoreCard(
    score: GameScore,
    modifier: Modifier = Modifier
) {

    val textScore  = "${score.correctAnswers}/${score.totalOfQuestions}"

    val getTextScoreSize: (String) -> TextUnit = { text ->

        if (text.length <= 3) {

            30.sp
        } else {

            22.sp
        }
    }

    Card(
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator(
                    modifier = Modifier
                        .size(90.dp),
                    trackColor = MaterialTheme.colorScheme.background,
                    progress = {
                        score.correctAnswers.toFloat() / score.totalOfQuestions.toFloat()
                    }
                )

                Text(
                    text = textScore,
                    fontWeight = FontWeight.Bold,
                    fontSize = getTextScoreSize(textScore),
                    textAlign = TextAlign.Center
                )
            }

            VerticalDivider(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .height(40.dp),
                thickness = 3.dp
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee(),
                    text = score.category.categoryName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee(),
                    text = score.type.description,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee(),
                    text = score.difficulty.description,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun MediumLengthScoreCardPreview() {

    KQuizTheme {

        ScoreCard(SelectionScreenPreviewData.mediumLengthGameScore)
    }
}

@PreviewLightDark
@Composable
fun ShortLengthScoreCardPreview() {

    KQuizTheme {

        ScoreCard(SelectionScreenPreviewData.shortLengthGameScore)
    }
}