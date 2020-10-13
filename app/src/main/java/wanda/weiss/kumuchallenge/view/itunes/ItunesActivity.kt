package wanda.weiss.kumuchallenge.view.itunes

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import wanda.weiss.kumuchallenge.R
import wanda.weiss.kumuchallenge.databinding.ActivityItunesBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind(this, R.layout.activity_itunes)

        initList()
        initObserver()
        getData()
    }

    private fun initObserver() {
        itunesObserver = Observer {
            when {
                it.result != null -> {
                    for (item in it.result.results){
                        itunesList.add(item)
                        binding.rvItunesData.adapter?.notifyItemInserted(itunesList.size - 1)
                    }
                }
                else -> {

                }
            }
        }
        vm.itunesAvailable.observe(this, Observer {
            vm.getItunes().observe(this, itunesObserver)
        })
    }

    private fun initList() {
        binding.rvItunesData.apply {
            layoutManager =
                LinearLayoutManager(this@ItunesActivity, LinearLayoutManager.VERTICAL, false)
            binding.rvItunesData.adapter = ItunesAdapter(itunesList)
        }
    }

    private fun getData() {
        vm.getItunesList()
    }
}