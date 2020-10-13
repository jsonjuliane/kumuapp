package wanda.weiss.kumuchallenge.view

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.gson.Gson
import dagger.android.support.DaggerAppCompatActivity
import wanda.weiss.kumuchallenge.App
import java.text.SimpleDateFormat

abstract class BaseActivity<B : ViewDataBinding> : DaggerAppCompatActivity() {
    open lateinit var binding: B

    lateinit var gson: Gson
    lateinit var app: App
    lateinit var sdf: SimpleDateFormat

    fun bind(activity: DaggerAppCompatActivity, @LayoutRes layout: Int) {
        binding = DataBindingUtil.setContentView(activity, layout)
        app = application as App
        gson = app.gson
        sdf = app.sdf
    }
}