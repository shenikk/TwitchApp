package com.example.twitchapp.presentation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Composable
fun GlideImage(
    url: String
): MutableState<ImageBitmap?> {

    val bitmapState: MutableState<ImageBitmap?> = remember {
        mutableStateOf(null)
    }
    val newUrl = url.replace("{width}", "400")
        .replace("{height}", "400")

    val context = LocalContext.current
    LaunchedEffect(context) {
        loadImage(context, newUrl, bitmapState)
    }

    return bitmapState
}

private fun loadImage(context: Context, url: String, bitmapState: MutableState<ImageBitmap?>) {
    Glide.with(context)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource.asImageBitmap()
            }

            override fun onLoadCleared(placeholder: Drawable?) {}

        })
}