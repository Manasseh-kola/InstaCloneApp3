package com.example.instacloneapp3.presentation.ui.bottom_sheets.profile_screen_bottom_sheets.user.manage_account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme

sealed class ManageAccount(val text: String, val icon : ImageVector){
    object SettingsAndPrivacy: ManageAccount("Settings and privacy", Icons.Outlined.PlayArrow)
    object ScheduledContent: ManageAccount("Scheduled content", Icons.Outlined.AddCircle)
    object UserActivity: ManageAccount("Your Activity", Icons.Outlined.Add)
    object Archive: ManageAccount("Archive", Icons.Outlined.Menu)
    object Insights: ManageAccount("Insights", Icons.Outlined.Send)
    object QRCode: ManageAccount("QRCode", Icons.Outlined.Build)
    object Saved: ManageAccount("Saved", Icons.Outlined.Notifications)
    object OrdersAndPayment: ManageAccount("Orders and payment", Icons.Outlined.ShoppingCart)
    object CloseFriends: ManageAccount("Close friends", Icons.Outlined.AccountBox)
    object Favourites: ManageAccount("favourites", Icons.Outlined.Favorite)
    object DiscoverPeople: ManageAccount("Discover people", Icons.Outlined.Person)
}

val manageAccount = listOf(
    ManageAccount.SettingsAndPrivacy,
    ManageAccount.ScheduledContent,
    ManageAccount.UserActivity,
    ManageAccount.Archive,
    ManageAccount.Insights,
    ManageAccount.QRCode,
    ManageAccount.Saved,
    ManageAccount.OrdersAndPayment,
    ManageAccount.CloseFriends,
    ManageAccount.Favourites,
    ManageAccount.DiscoverPeople
)

@Composable
fun ManageAccountItem(text: String, icon: ImageVector){
    Row(
        modifier = Modifier
            .padding(start = 10.dp)
            .fillMaxWidth()

    ){
        Icon(imageVector = icon, contentDescription = "")
        Spacer(Modifier.width(8.dp))
        Text(text = text)
    }
}

@Composable
fun ManageUserAccount(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ){

        Divider(Modifier.padding(bottom = 10.dp))
        for (content in manageAccount){
            ManageAccountItem(text = content.text, icon = content.icon)
            Divider(Modifier.padding(vertical = 10.dp))
        }
        Spacer(Modifier.height(40.dp))
    }
}


@Composable
@Preview(showBackground = true)
fun CreateNewContentPreview(){
    InstaCloneApp3Theme {
        ManageUserAccount()
    }
}