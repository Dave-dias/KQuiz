package com.portifolio.kquiz.quiz.presentation.selection_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.portifolio.kquiz.R
import com.portifolio.kquiz.quiz.presentation.core.QuestionScreenRoute
import com.portifolio.kquiz.quiz.presentation.core.components.BottomBarWithButton
import com.portifolio.kquiz.quiz.presentation.selection_screen.components.ScoreCard
import com.portifolio.kquiz.quiz.presentation.selection_screen.components.SelectionScreenBottomSheetContent
import com.portifolio.kquiz.quiz.presentation.selection_screen.viewmodel.SelectionScreenEvent
import com.portifolio.kquiz.quiz.presentation.selection_screen.viewmodel.SelectionScreenIntent
import com.portifolio.kquiz.quiz.presentation.selection_screen.viewmodel.SelectionScreenState
import com.portifolio.kquiz.quiz.presentation.selection_screen.viewmodel.SelectionScreenViewModel

@Composable
fun SelectionScreenRoot(
    viewModel: SelectionScreenViewModel,
    navController: NavController
) {

    val state = viewModel.state.collectAsState()
    val effect = viewModel.effect

    LaunchedEffect(Unit) {

        effect.collect { effect ->

            when (effect) {

                is SelectionScreenEvent.NavigateToQuestionScreen -> navController.navigate(
                    QuestionScreenRoute(
                        numberOfQuestions = effect.questionPerRound,
                        category = effect.category,
                        difficulty = effect.difficulty,
                        type = effect.type
                    )
                )
            }
        }
    }

    SelectionScreen(
        state = state.value,
        onIntent = { intent ->
            viewModel.processIntent(intent)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionScreen(
    state: SelectionScreenState,
    onIntent: (SelectionScreenIntent) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {

            if (state.isLoading) {

                return@Scaffold
            }

            TopBar(scrollBehavior)
        },
        bottomBar = {

            if (state.isLoading) {

                return@Scaffold
            }

            BottomBar(
                isSheetOpen,
                onClick = {
                    isSheetOpen = true
                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {

            if (state.isLoading) {

                CircularProgressIndicator()
            } else {

                LoadedScreen(
                    scrollBehavior = scrollBehavior,
                    state = state,
                    sheetState = sheetState,
                    isSheetOpen = isSheetOpen,
                    onIntent = onIntent,
                    onDismissSheet = {
                        isSheetOpen = false
                    }
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(scrollBehavior: TopAppBarScrollBehavior) {

    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {

            Text(
                text = "KQuiz",
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    )
}

@Composable
private fun BottomBar(isSheetOpen: Boolean, onClick: () -> Unit) {

    BottomBarWithButton(
        textButton = stringResource(R.string.play),
        onClick = onClick,
        iconButton = {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = stringResource(R.string.play)
            )
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LoadedScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    state: SelectionScreenState,
    isSheetOpen: Boolean,
    sheetState: SheetState,
    onIntent: (SelectionScreenIntent) -> Unit,
    onDismissSheet: () -> Unit
) {

    val scrollState = rememberScrollState()

    val navigationBarPaddingValues = WindowInsets.navigationBars.asPaddingValues()
    val localLayoutDirection = LocalLayoutDirection.current

    val navigationBarBottomPaddingValues = navigationBarPaddingValues.calculateBottomPadding().value
    val navigationBarLeftPaddingValues =
        navigationBarPaddingValues.calculateLeftPadding(localLayoutDirection).value
    val navigationBarRightPaddingValues =
        navigationBarPaddingValues.calculateRightPadding(localLayoutDirection).value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 10.dp),
    ) {

        item {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.recent_scores),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

        }

        items(state.recentGameScores) { score ->

            ScoreCard(score = score)
        }
    }

    if (isSheetOpen) {

        ModalBottomSheet(
            sheetState = sheetState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = navigationBarLeftPaddingValues.dp,
                    end = navigationBarRightPaddingValues.dp,
                    bottom = navigationBarBottomPaddingValues.dp
                ),
            onDismissRequest = onDismissSheet
        ) {

            SelectionScreenBottomSheetContent(
                scrollState = scrollState,
                state = state,
                onIntent = onIntent
            )
        }
    }
}

@PreviewLightDark
@Composable
fun LoadingSelectionScreenPreview() {

    SelectionScreen(
        state = SelectionScreenPreviewData.loadingSelectionScreenState,
        onIntent = {}
    )
}

@PreviewLightDark
@Composable
fun DefaultSelectionScreenPreview() {

    SelectionScreen(
        state = SelectionScreenPreviewData.defaultSelectionScreenState,
        onIntent = {}
    )
}