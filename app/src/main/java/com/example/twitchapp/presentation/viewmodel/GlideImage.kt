package com.example.twitchapp.presentation.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Composable
fun GlidePicture(
    url: String
): MutableState<ImageBitmap?> {

    val bitmapState: MutableState<ImageBitmap?> = remember {
        mutableStateOf(null)
    }
    val newUrl = url.replace("{width}", "400")
        .replace("{height}", "400")

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(newUrl)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource.asImageBitmap()
            }
            override fun onLoadCleared(placeholder: Drawable?) {}

        })

    return bitmapState
}