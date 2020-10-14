package wanda.weiss.kumuchallenge.viewmodel

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.PublishSubject
import wanda.weiss.kumuchallenge.App
import wanda.weiss.kumuchallenge.model.observable.ItunesObservable
import wanda.weiss.kumuchallenge.model.pojo.ItunesWrapper
import wanda.weiss.kumuchallenge.model.pojo.Result
import wanda.weiss.kumuchallenge.model.repository.ItunesRepo



@SuppressLint("CheckResult")
class ItunesVM(
    app: App,
    private val itunesRepo: ItunesRepo
) : BaseViewModel(app) {

    private lateinit var itunesResult: LiveData<ItunesWrapper>
    private lateinit var observable: ItunesObservable

    private val autoCompletePublishSubject = PublishSubject.create<String>()
    var itunesAvailable = MutableLiveData<Boolean>()
    var searchEmpty = MutableLiveData<Boolean>()
    var itemClicked = MutableLiveData<Result>()

    init {
        configureInterceptor(autoCompletePublishSubject, 400)
            .subscribe { result -> onTextFiltered(result) }
    }

    fun initObservable(observable: ItunesObservable) {
        this.observable = observable
    }

    private fun getItunesList(term: String) {
        itunesResult = itunesRepo.getItunes(term)
        itunesAvailable.value = true
    }

    fun getItunes(): LiveData<ItunesWrapper> {
        return itunesResult
    }

    fun onTextChanged(charSequence: CharSequence) {
        if (charSequence.isEmpty()) dispose()
        searchEmpty.value = charSequence.isEmpty()
        observable.searchEmpty = charSequence.isEmpty()
        autoCompletePublishSubject.onNext(charSequence.toString())
    }

    private fun onTextFiltered(result: String) {
        getItunesList(result)
    }

    fun onItemClicked(v: View) {
        val item = (v.tag as Result)
        itemClicked.value = item
    }

    private fun dispose() {
        itunesRepo.dispose()
    }

}