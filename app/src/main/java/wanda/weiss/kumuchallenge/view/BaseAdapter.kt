package wanda.weiss.kumuchallenge.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.webkit.URLUtil
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import wanda.weiss.kumuchallenge.R

abstract class BaseRecyclerAdapter<B : ViewDataBinding> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    open lateinit var binding: B
    private lateinit var context: Context
    private var lastPosition = 0

    fun bind(parent: ViewGroup, layout: Int): ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, layout, parent, false)
        return ViewHolder(binding.root)
    }

    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)

    fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
//            val animation = AnimationUtils.loadAnimation(context, R.anim.list_animation_fall_down)
//            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

//    fun loadImage(url: String, view: ImageView, imageSize: String? = null) {
//        val fileName = URLUtil.guessFileName(url, null, null)
//        val rawFileName = fileName.substring(0, fileName.indexOf("."))
//        val extension = fileName.substring(fileName.indexOf(".") + 1)
//        val updatedURL = when (imageSize) {
//            null -> url
//            else -> url.replace(fileName, "$rawFileName-$imageSize.$extension")
//        }
//        try {
//            GlideApp.with(context.applicationContext)
//                .load(updatedURL)
//                .placeholder(R.drawable.ic_placeholder_food)
//                .error(R.drawable.ic_placeholder_food)
//                .signature(ObjectKey(System.currentTimeMillis().toString()))
//                .optionalCenterCrop()
//                .listener(object : RequestListener<Drawable> {
//                    override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//                        return false
//                    }
//
//                    override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                        if (imageSize != "image")
//                            Handler().post {
//                                reloadImage(url, view)
//                            }
//                        return false
//                    }
//                })
//                .into(view)
//        }catch (ignored: Exception){
//            //Handle for context issue, should not load image
//        }
//    }

}