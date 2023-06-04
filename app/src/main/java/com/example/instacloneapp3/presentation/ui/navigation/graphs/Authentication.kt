package com.example.instacloneapp3.presentation.ui.navigation.graphs


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.instacloneapp3.presentation.ui.screens.authentication_screens.LoginScreen
import com.example.instacloneapp3.presentation.ui.screens.authentication_screens.ResetPasswordScreen

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object ResetPassword : AuthScreen("reset_password")

}

fun NavGraphBuilder.authenticationGraph(
    navigateToRoute: (String) -> Unit,
    backNavigation: (String, String) -> Unit
){
    navigation(startDestination = "home", route = "authenticated"){
        composable(AuthScreen.Login.route){ LoginScreen()}
        composable(AuthScreen.ResetPassword.route){ ResetPasswordScreen()}
    }
}