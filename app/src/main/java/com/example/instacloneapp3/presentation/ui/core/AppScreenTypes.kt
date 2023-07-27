package com.example.instacloneapp3.presentation.ui.core

import com.example.instacloneapp3.R

sealed class AppScreenTypes(
    val route: String,
    val icon : Int? = null,
    val args: String? = null,
    val userIndex: Int? = null,
    //Index of Screen in backStack
    val screenIndex: Int? = null,
    val selectedIcon: Int? = null,

) {

    class Messages(screenIndex: Int = 0): AppScreenTypes(
        route = "messages",
        screenIndex = screenIndex,
    )
    object FavoriteFeeds : AppScreenTypes(route = "favorites")
    object Notifications: AppScreenTypes(route = "notifications")
    class DirectMessages(screenIndex: Int): AppScreenTypes(
        route = "directMessages",
        screenIndex = screenIndex,
    )
    class UsersProfile(args: String?, screenIndex: Int? = null): AppScreenTypes(
        args = args,
        route = "usersProfile",
        screenIndex = screenIndex,
    )
    class UserRelationShip(args: String): AppScreenTypes(route = "userRelationship", args = args)
    class UsersRelationShip(
        args: String,
        userIndex: Int,
    ): AppScreenTypes(route = "userRelationship", args = args, userIndex = userIndex)



    //Bottom NavBar routes
    class Home(screenIndex: Int = 0): AppScreenTypes(
        route = "home",
        screenIndex = screenIndex,
        icon = R.drawable.ic_outlined_home,
        selectedIcon = R.drawable.ic_filled_home
    )
    object New: AppScreenTypes(
        route = "newPost",
        icon = R.drawable.ic_outlined_add_box,
        selectedIcon = R.drawable.ic_filled_add_box
    )
    class Reels(screenIndex: Int = 0): AppScreenTypes(
        route = "reels",
        screenIndex = screenIndex,
        icon = R.drawable.ic_outlined_reels,
        selectedIcon = R.drawable.ic_filled_reels
    )

    class Search(screenIndex: Int = 0): AppScreenTypes(
        route = "search",
        screenIndex = screenIndex,
        icon = R.drawable.ic_outlined_search,
        selectedIcon = R.drawable.ic_filled_search
    )
    class UserProfile(screenIndex: Int = 0): AppScreenTypes(
        route = "profile",
        screenIndex = screenIndex,
        icon = R.drawable.ic_outlined_account_circle,
        selectedIcon = R.drawable.ic_filled_account_circle
    )

}

