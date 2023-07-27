package com.example.instacloneapp3.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.example.instacloneapp3.presentation.ui.navigation.graphs.authenticatedGraph
import com.example.instacloneapp3.presentation.ui.bottom_sheets.AppBottomSheets
import com.example.instacloneapp3.presentation.ui.core.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.NavigationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App( navigationViewModel: NavigationViewModel = hiltViewModel() ){

    val appState = rememberAppState()
    val navController = appState.navController
    val sheetState = rememberModalBottomSheetState()
    val navigationUiState = navigationViewModel.navigationState.collectAsState()

    //App root Composable
    Scaffold { innerPadding ->
        //Nav Host to Display current destination
        NavHost(
            navController = navController,
            startDestination = "authenticated",
            modifier = Modifier
                .padding(innerPadding)
        ) { authenticatedGraph(
            rootNavController = navController,
            navigationViewModel = navigationViewModel,
        ) }

        //Bottom Sheets
        if (navigationUiState.value.showBottomSheet) {
            AppBottomSheets(sheetState = sheetState)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AppPreview(){
    InstaCloneApp3Theme { App() }
}