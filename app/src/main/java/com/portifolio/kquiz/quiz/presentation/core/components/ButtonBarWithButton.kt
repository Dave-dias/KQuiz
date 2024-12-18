package com.portifolio.kquiz.quiz.presentation.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portifolio.kquiz.R
import com.portifolio.kquiz.core.theme.KQuizTheme

@Composable
fun BottomBarWithButton(
    modifier: Modifier = Modifier,
    textButton: String,
    iconButton: (@Composable () -> Unit)?,
    isButtonEnabled: Boolean = true,
    onClick: () -> Unit
) {

    BottomAppBar {

        Button(
            modifier = modifier
                .padding(bottom = 5.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            enabled = isButtonEnabled,
            onClick = onClick
        ) {

            Row(
                Modifier
                    .padding(vertical = 5.dp, horizontal = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                iconButton?.let {

                    iconButton()
                    Spacer(modifier = Modifier.width(5.dp))
                }

                Text(
                    text = textButton,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun BottomBarWithButtonPreview(modifier: Modifier = Modifier) {

    KQuizTheme {

        BottomBarWithButton(
            textButton = "Next",
            iconButton = @Composable {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = "Next"
                )
            },
            isButtonEnabled = true,
            onClick = {}
        )
    }
}