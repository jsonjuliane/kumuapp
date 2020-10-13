package wanda.weiss.kumuchallenge.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.PublishSubject
import wanda.weiss.kumuchallenge.App
import wanda.weiss.kumuchallenge.model.pojo.ItunesWrapper
import wanda.weiss.kumuchallenge.model.repository.ItunesRepo

@SuppressLint("CheckResult")
class ItunesVM(app: App, private val itunesRepo: ItunesRepo) : BaseViewModel(app) {

    private lateinit var itunesResult: LiveData<ItunesWrapper>
    private val autoCompletePublishSubject = PublishSubject.create<String>()
    var itunesAvailable = MutableLiveData<Boolean>()
    var searchEmpty = MutableLiveData<Boolean>()

    init {
        configureInterceptor(autoCompletePublishSubject, 400)
            .subscribe { result -> onTextFiltered(result) }
    }

    private fun getItunesList(term: String) {
        itunesResult = itunesRepo.getItunes(term)
        itunesAvailable.value = true
    }

    fun getItunes(): LiveData<ItunesWrapper> {
        return itunesResult
    }

    fun onTextChanged(charSequence: CharSequence) {
        searchEmpty.value = charSequence.isEmpty()
        autoCompletePublishSubject.onNext(charSequence.toString())
    }

    private fun onTextFiltered(result: String) {
        getItunesList(result)
    }

}