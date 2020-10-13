package wanda.weiss.kumuchallenge.view.itunes

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import wanda.weiss.kumuchallenge.R
import wanda.weiss.kumuchallenge.databinding.ItemItunesBinding
import wanda.weiss.kumuchallenge.di.module.GlideApp
import wanda.weiss.kumuchallenge.model.pojo.Result
import wanda.weiss.kumuchallenge.view.BaseRecyclerAdapter

class ItunesAdapter(private var context: Context, private var itunesList: ArrayList<Result>) :
    BaseRecyclerAdapter<ItemItunesBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return bind(parent, R.layout.item_itunes)
    }

    override fun getItemCount(): Int {
        return itunesList.size
    }

    override fun getItemId(position: Int): Long {
        return System.currentTimeMillis()
    }

    override fun getItemViewType(position: Int): Int {
        return System.currentTimeMillis().toInt()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itunesList[position]
        binding.tvItunesTrackName.text = item.trackName
        binding.tvItunesTrackPrice.text = "PHP${item.trackPrice}"
        binding.tvItunesTrackGenre.text = item.primaryGenreName

        when {
            item.artworkUrl100 != null && item.artworkUrl100.isNotEmpty() -> GlideApp.with(context.applicationContext)
                .load(item.artworkUrl100)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.ivItunesTrackArtwork.setImageDrawable(
                            ResourcesCompat.getDrawable(
                                context.resources,
                                R.drawable.ic_movie,
                                null
                            )
                        )
                        return false
                    }
                })
                .into(binding.ivItunesTrackArtwork)
            else -> binding.ivItunesTrackArtwork.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_movie,
                    null
                )
            )
        }
    }
}