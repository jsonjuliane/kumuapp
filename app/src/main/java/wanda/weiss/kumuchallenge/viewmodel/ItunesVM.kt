package wanda.weiss.kumuchallenge.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import wanda.weiss.kumuchallenge.App
import wanda.weiss.kumuchallenge.model.pojo.ItunesWrapper
import wanda.weiss.kumuchallenge.model.repository.ItunesRepo

class ItunesVM(app: App, private val itunesRepo: ItunesRepo) : AndroidViewModel(app) {

    private lateinit var itunesResult: LiveData<ItunesWrapper>
    var itunesAvailable = MutableLiveData<Boolean>()

    fun getItunesList() {
        itunesResult = itunesRepo.getItunes()
        itunesAvailable.value = true
    }

    fun getItunes(): LiveData<ItunesWrapper> {
        return itunesResult
    }

}