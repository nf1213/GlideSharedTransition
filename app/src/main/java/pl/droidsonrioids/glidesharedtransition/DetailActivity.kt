package pl.droidsonrioids.glidesharedtransition

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.Fade
import android.transition.TransitionSet
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private val num by lazy {
        intent.getIntExtra(IMAGE_NUM_KEY, 1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportPostponeEnterTransition()
        detailImage.transitionName = "$num"

        window.sharedElementEnterTransition = TransitionSet()
                .addTransition(ChangeImageTransform())
                .addTransition(ChangeBounds())

        window.enterTransition = Fade().apply {
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
            excludeTarget(R.id.action_bar_container, true)
        }

        detailImage.maximumScale = 10f
        detailImage.setZoomable(false)
        detailImage.scaleType = ImageView.ScaleType.CENTER_CROP

        val thumbnailRequest = Glide.with(this)
                .load(thumbnailUrl(num))
                .apply(RequestOptions.noTransformation().onlyRetrieveFromCache(true))
                .listener(onResourceReady = {
                    supportStartPostponedEnterTransition()
                }, onLoadFailed = {
                    supportStartPostponedEnterTransition()
                })

        Glide.with(this)
                .load(fullSizeUrl(num))
//                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .thumbnail(thumbnailRequest)
                .listener({
                              detailImage.setZoomable(true)
                          })
                .into(detailImage)
    }
}
