package pl.droidsonrioids.glidesharedtransition

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun RequestBuilder<Drawable>.listener(onResourceReady: (requestBuilder: RequestBuilder<Drawable>) -> Unit,
                                      onLoadFailed: ((requestBuilder: RequestBuilder<Drawable>) -> Unit) = {}) :
        RequestBuilder<Drawable> {

    val listener = object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            onLoadFailed(this@listener)
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            onResourceReady(this@listener)
            return false
        }
    }

    return this.listener(listener)
}