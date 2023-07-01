package com.example.instacloneapp3.presentation.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation


open class Screens(open val route: String){}

sealed class HomeScreens(override val route: String, val navArgs: String? = null):Screens(route){
    object Home : HomeScreens("home")
    object Reels : HomeScreens("reels")
    object AdTools: HomeScreens("adTools")
    object UserProfile : HomeScreens("userProfile")
    object UsersProfile: HomeScreens("usersProfile")
    object Notifications: HomeScreens("notifications")
    object FollowingFeeds: HomeScreens("followingFeeds")
    object FavoritesFeeds: HomeScreens("favoritesFeeds")
}

//fun NavGraphBuilder.homeScreenGraph(){
//    navigation(startDestination = "home", route = "home"){
//        composable(HomeScreens.Home.route){}
//        composable(HomeScreens.Reels.route){}
//        composable(HomeScreens.AdTools.route){}
//        composable(HomeScreens.UserProfile.route){}
//        composable(HomeScreens.UsersProfile.route){}
//        composable(HomeScreens.Notifications.route){}
//        composable(HomeScreens.FollowingFeeds.route){}
//        composable(HomeScreens.FavoritesFeeds.route){}
//    }
//}