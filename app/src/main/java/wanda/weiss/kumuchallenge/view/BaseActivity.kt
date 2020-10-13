package wanda.weiss.kumuchallenge.view

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.gson.Gson
import dagger.android.support.DaggerAppCompatActivity
import wanda.weiss.kumuchallenge.App
import java.text.SimpleDateFormat
import java.util.*

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

    fun hideSoftKeyboard(v: View?) {
        if (v != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            Objects.requireNonNull(imm).hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}