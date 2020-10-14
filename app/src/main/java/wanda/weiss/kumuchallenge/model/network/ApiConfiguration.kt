package wanda.weiss.kumuchallenge.model.network

import wanda.weiss.kumuchallenge.BuildConfig
import javax.inject.Inject

class ApiConfiguration @Inject constructor() {

    val baseUrl: String
        get() = BuildConfig.BASE_URL

}