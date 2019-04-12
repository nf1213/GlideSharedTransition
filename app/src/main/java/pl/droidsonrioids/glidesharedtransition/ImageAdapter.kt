package pl.droidsonrioids.glidesharedtransition

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions

const val NUM_IMAGES = 48
const val IMAGES_OFFSET = 169
fun thumbnailUrl(num: Int) = "https://picsum.photos/300/210?image=$num"
fun fullSizeUrl(num: Int) = "https://picsum.photos/3000/2100?image=$num"

class ImageAdapter(private val onClick: (Int, View) -> Unit) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount() = NUM_IMAGES

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) = holder.bind(position)

    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(num: Int) {
            val offsetNum = IMAGES_OFFSET + num
            (itemView as ImageView).apply {
                Glide.with(this)
                        .load(thumbnailUrl(offsetNum))
                        .apply(RequestOptions.overrideOf(width).downsample(DownsampleStrategy.CENTER_INSIDE))
                        .into(this)
                transitionName = "$offsetNum"
                setOnClickListener { onClick(offsetNum, it) }
            }
        }
    }
}