package pl.droidsonrioids.glidesharedtransition

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

const val IMAGE_NUM_KEY = "img_num"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        images.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = ImageAdapter(::goToDetails)
        }

        val workRequest = OneTimeWorkRequest.Builder(ImageDownloader::class.java).build()
        WorkManager.getInstance().enqueue(workRequest)
    }

    private fun goToDetails(num: Int, imageView: View) {
        val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, imageView.transitionName).toBundle()
        Intent(this, DetailActivity::class.java)
                .putExtra(IMAGE_NUM_KEY, num)
                .let {
                    startActivity(it, options)
                }
    }
}