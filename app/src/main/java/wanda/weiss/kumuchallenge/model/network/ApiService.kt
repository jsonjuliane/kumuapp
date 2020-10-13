package wanda.weiss.kumuchallenge.model.network

import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query
import wanda.weiss.kumuchallenge.model.pojo.ItunesData
import wanda.weiss.kumuchallenge.model.pojo.Result

interface ApiService {

    @GET("/search?country=au&media=movie&all")
    fun getItunesList(@Query("term") term: String): Maybe<ItunesData>

}