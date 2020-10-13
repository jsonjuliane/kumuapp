package wanda.weiss.kumuchallenge.view.itunes

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import timber.log.Timber
import wanda.weiss.kumuchallenge.R
import wanda.weiss.kumuchallenge.databinding.ActivityItunesBinding
import wanda.weiss.kumuchallenge.model.observable.ItunesObservable
import wanda.weiss.kumuchallenge.model.pojo.ItunesWrapper
import wanda.weiss.kumuchallenge.model.pojo.Result
import wanda.weiss.kumuchallenge.view.BaseActivity
import wanda.weiss.kumuchallenge.viewmodel.ItunesVM
import javax.inject.Inject

class ItunesActivity : BaseActivity<ActivityItunesBinding>() {
    private lateinit var itunesObserver: Observer<ItunesWrapper>
    private var itunesList: ArrayList<Result> = arrayListOf()

    @Inject
    lateinit var vm: ItunesVM

    @Inject
    lateinit var ob: ItunesObservable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind(this, R.layout.activity_itunes)
        binding.vm = vm
        binding.ob = ob
        vm.initObservable(ob)

        initList()
        initObserver()
    }

    private fun initObserver() {
        itunesObserver = Observer {
            binding.ivItunesLoading.visibility = View.INVISIBLE
            when {
                it.result != null -> {
                    when {
                        it.result.results.isNotEmpty() -> {
                            itunesList.addAll(it.result.results)
                            binding.rvItunesData.adapter?.notifyDataSetChanged()
                            ob.searchEmpty = false
                        }
                        else -> ob.searchEmpty = true
                    }
                }
                else -> {

                }
            }
        }
        vm.itunesAvailable.observe(this, Observer {
            binding.ivItunesLoading.visibility = View.VISIBLE
            vm.getItunes().observe(this, itunesObserver)
        })

        vm.searchEmpty.observe(this, Observer {
            clearList()
        })

        vm.itemClicked.observe(this, Observer {
            hideSoftKeyboard(binding.rvItunesData)
        })
    }

    private fun initList() {
        binding.rvItunesData.apply {
            layoutManager =
                LinearLayoutManager(this@ItunesActivity, LinearLayoutManager.VERTICAL, false)
            binding.rvItunesData.adapter = ItunesAdapter(this@ItunesActivity, itunesList, vm)
        }
    }

    private fun clearList(){
        itunesList.clear()
        binding.rvItunesData.adapter?.notifyDataSetChanged()
    }
}
