package pl.droidsonrioids.glidesharedtransition

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.squareup.picasso.Picasso

class ImageDownloader(context: Context, workerParameters: WorkerParameters): Worker(context, workerParameters) {
    override fun doWork(): Result {
        for (i in IMAGES_OFFSET..IMAGES_OFFSET + NUM_IMAGES) {
            if (i % 4 == 0) {
                Picasso.with(applicationContext)
                        .load(fullSizeUrl(i))
                        .fetch()
            }
        }
        return Result.success()
    }
}