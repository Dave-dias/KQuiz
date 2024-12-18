package com.portifolio.kquiz.quiz.presentation.selection_screen.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.portifolio.kquiz.R
import com.portifolio.kquiz.core.theme.KQuizTheme
import com.portifolio.kquiz.quiz.domain.model.CategoryEnum
import com.portifolio.kquiz.quiz.presentation.selection_screen.SelectionScreenPreviewData
import com.portifolio.kquiz.quiz.presentation.selection_screen.viewmodel.SelectionScreenIntent
import com.portifolio.kquiz.quiz.presentation.selection_screen.viewmodel.SelectionScreenState

@Composable
fun SelectionScreenBottomSheetContent(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    state: SelectionScreenState,
    onIntent: (SelectionScreenIntent) -> Unit
) {

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {

        Text(
            text = stringResource(R.string.select_the_number_of_questions),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {

            state.questionPerRoundEnum.forEachIndexed { index, questionPerRound ->

                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = state.questionPerRoundEnum.count()
                    ),
                    selected = state.selectedQuestionsPerRound == questionPerRound,
                    onClick = {
                        onIntent(
                            SelectionScreenIntent.SelectedQuestionsPerRound(
                                questionPerRound
                            )
                        )
                    }
                ) {

                    Text(text = questionPerRound.number.toString())
                }
            }
        }

        Text(
            text = stringResource(R.string.select_the_number_of_possible_answers),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {

            state.types.forEachIndexed { index, type ->

                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = state.types.count()
                    ),
                    selected = state.selectedType == type,
                    onClick = {
                        onIntent(SelectionScreenIntent.SelectedType(type))
                    }
                ) {

                    Text(text = type.description)
                }
            }
        }

        Text(
            text = stringResource(R.string.select_a_level),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {

            state.difficulties.forEachIndexed { index, difficulty ->

                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = state.difficulties.count()
                    ),
                    selected = state.selectedDifficulty == difficulty,
                    onClick = {
                        onIntent(SelectionScreenIntent.SelectedDifficulty(difficulty))
                    }
                ) {

                    Text(text = difficulty.description)
                }
            }
        }

        Text(
            text = stringResource(R.string.select_a_category),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        CategoryDropDownMenu(
            categories = state.categories,
            selectedCategory = state.selectedCategory,
            onCategorySelected = { category ->
                onIntent(SelectionScreenIntent.SelectedCategory(category))
            },
        )

        HorizontalDivider(Modifier.padding(vertical = 20.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                onIntent(SelectionScreenIntent.SelectedPlay)
            }
        ) {

            Row(
                Modifier
                    .padding(vertical = 5.dp, horizontal = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = stringResource(R.string.play)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = stringResource(R.string.play),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropDownMenu(
    categories: List<CategoryEnum>,
    selectedCategory: CategoryEnum,
    onCategorySelected: (CategoryEnum) -> Unit,
    modifier: Modifier = Modifier
) {

    val expanded = remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier.fillMaxWidth(),
        expanded = expanded.value,
        onExpandedChange = {
            expanded.value = !expanded.value
        }
    ) {

        OutlinedTextField(
            value = selectedCategory.categoryName,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
        )

        ExposedDropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {

            categories.forEach { category ->

                DropdownMenuItem(
                    text = {
                        Text(text = category.categoryName)
                    },
                    onClick = {
                        onCategorySelected(category)
                        expanded.value = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectionScreenBottomSheetContentPreview() {

    KQuizTheme {

        SelectionScreenBottomSheetContent(
            state = SelectionScreenPreviewData.defaultSelectionScreenState,
            onIntent = {}
        )
    }
}