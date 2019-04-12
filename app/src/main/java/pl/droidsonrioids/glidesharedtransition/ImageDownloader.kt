package pl.droidsonrioids.glidesharedtransition

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bumptech.glide.Glide

class ImageDownloader(context: Context, workerParameters: WorkerParameters): Worker(context, workerParameters) {
    override fun doWork(): Result {
        for (i in IMAGES_OFFSET..IMAGES_OFFSET + NUM_IMAGES) {
            if (i % 4 == 0) {
                Glide.with(applicationContext)
                        .downloadOnly()
                        .load(fullSizeUrl(i))
                        .submit(3000, 2100)
                        .get()

            }
        }
        return Result.success()
    }
}