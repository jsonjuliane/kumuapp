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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}