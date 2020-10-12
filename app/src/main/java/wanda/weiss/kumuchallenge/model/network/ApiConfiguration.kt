package wanda.weiss.kumuchallenge.model.network

import android.content.Context
import wanda.weiss.kumuchallenge.BuildConfig
import javax.inject.Inject

class ApiConfiguration @Inject constructor(context: Context) {

    val baseUrl: String
        get() = BuildConfig.BASE_URL

}