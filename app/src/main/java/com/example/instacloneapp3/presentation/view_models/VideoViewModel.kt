package com.example.instacloneapp3.presentation.view_models

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.instacloneapp3.R
import com.example.instacloneapp3.presentation.states.VideoItem
import com.google.common.reflect.Reflection.getPackageName
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val player: Player
): ViewModel() {

    init{
        player.prepare()
        player.playWhenReady = true
        player.repeatMode = Player.REPEAT_MODE_ONE
    }

    private val packageName = "com.example.instacloneapp3.presentation.view_models"
    private val videoUriLocal = Uri.parse("android.resource://" + packageName + "/" + R.raw.textvideo)
    private val videoItem = VideoItem(
        contentUri = videoUriLocal,
        mediaItem = MediaItem.fromUri(videoUriLocal),
        name = "video"
    )


    fun playVideo(){
        player.setMediaItem(videoItem.mediaItem)
        player.play()
    }

    fun releasePlayer(){
        if (player.currentMediaItem != null){
            player.pause()
        }

    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}