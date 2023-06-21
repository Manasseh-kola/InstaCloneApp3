package com.example.instacloneapp3.presentation.ui.components

import android.net.Uri
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.instacloneapp3.presentation.ui.theme.InstaCloneApp3Theme
import com.example.instacloneapp3.presentation.view_models.VideoViewModel


@Composable
fun VideoPlayer(
    videoViewModel: VideoViewModel = hiltViewModel()
){

    LaunchedEffect(key1 = Unit){
        videoViewModel.playVideo()
    }

    Log.i("player", "${videoViewModel.player.currentMediaItem}")

    DisposableEffect(key1 = Unit){
        onDispose { videoViewModel.releasePlayer() }
    }

    AndroidView(
        factory = {context ->
            PlayerView(context).also{playerView ->
                playerView.useController = true
                playerView.player = videoViewModel.player
            }
        },
        modifier = Modifier
            .fillMaxSize()
    )
}


@Composable
@Preview(showBackground = true)
fun VideoPlayerPreview(){
    InstaCloneApp3Theme() {

        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            VideoPlayer()
        }
    }
}