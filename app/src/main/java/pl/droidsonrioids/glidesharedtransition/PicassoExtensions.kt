package pl.droidsonrioids.glidesharedtransition

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.RequestCreator

fun RequestCreator.into(target: ImageView, onSuccess: () -> Unit, onError: () -> Unit = {}) {
    return this.into(target, object: Callback {
        override fun onSuccess() {
            onSuccess()
        }

        override fun onError() {
            onError()
        }
    })
}