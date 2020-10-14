package wanda.weiss.kumuchallenge.model.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import wanda.weiss.kumuchallenge.App
import wanda.weiss.kumuchallenge.model.db.AppDatabase
import wanda.weiss.kumuchallenge.model.pojo.ItunesWrapper
import wanda.weiss.kumuchallenge.model.pojo.Result

/*
    F
 */

class ItunesRepo(
    private val app: App,
    private val db: AppDatabase
) : BaseRepository() {

    private var itunesDisposable: Disposable? = null
    private var localItunesDisposable: Disposable? = null
    var itunesResult = MutableLiveData<ItunesWrapper>()

    fun getItunes(term: String): MutableLiveData<ItunesWrapper> {
        itunesDisposable = app.apiService.getItunesList(term)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                itunesResult.postValue(ItunesWrapper(it, null))
                saveTracks(it.results as ArrayList<Result>)
            }, {
                itunesResult.postValue(ItunesWrapper(null, it))
            })
        return itunesResult
    }

    private fun saveTracks(arrayList: ArrayList<Result>) {
        localItunesDisposable = Maybe.fromCallable {
            db.tracksDao().insert(*arrayList.toTypedArray())
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {
            })
    }

    fun dispose() {
        itunesDisposable?.dispose()
        localItunesDisposable?.dispose()
    }
}