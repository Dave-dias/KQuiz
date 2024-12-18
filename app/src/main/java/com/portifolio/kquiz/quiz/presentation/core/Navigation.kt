package com.portifolio.kquiz.quiz.presentation.core

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.portifolio.kquiz.quiz.domain.model.CategoryEnum
import com.portifolio.kquiz.quiz.domain.model.DifficultyEnum
import com.portifolio.kquiz.quiz.domain.model.TypeEnum
import com.portifolio.kquiz.quiz.presentation.question_screen.QuestionScreenRoot
import com.portifolio.kquiz.quiz.presentation.selection_screen.SelectionScreenRoot
import kotlin.reflect.typeOf

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SelectionScreenRoute) {

        composable<SelectionScreenRoute>{

            SelectionScreenRoot(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }

        composable<QuestionScreenRoute>(
            typeMap = mapOf(
                typeOf<Int>() to NavType.IntType,
                typeOf<CategoryEnum>() to NavType.EnumType(CategoryEnum::class.java),
                typeOf<DifficultyEnum>() to NavType.EnumType(DifficultyEnum::class.java),
                typeOf<TypeEnum>() to NavType.EnumType(TypeEnum::class.java)
            )
        ){

            QuestionScreenRoot(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }
    }
}