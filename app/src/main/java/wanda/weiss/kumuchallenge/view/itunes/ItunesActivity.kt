package wanda.weiss.kumuchallenge.view.itunes

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.sheet_itunes_detail.view.*
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

    private fun initList() {
        binding.rvItunesData.apply {
            layoutManager =
                LinearLayoutManager(this@ItunesActivity, LinearLayoutManager.VERTICAL, false)
            binding.rvItunesData.adapter = ItunesAdapter(this@ItunesActivity, itunesList, vm)
        }
    }

    private fun initObserver() {
        binding.supBottomSheet.setFadeOnClickListener {
            binding.supBottomSheet.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }

        binding.supBottomSheet.addPanelSlideListener(object :
            SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View?, slideOffset: Float) {}

            override fun onPanelStateChanged(
                panel: View?,
                previousState: SlidingUpPanelLayout.PanelState?,
                newState: SlidingUpPanelLayout.PanelState?
            ) {
                if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    binding.supBottomSheet.iv_sheet_video_cover.visibility = View.VISIBLE
                    binding.supBottomSheet.vv_sheet_preview.stopPlayback()
                }
            }

        })

        binding.supBottomSheet.vv_sheet_preview.setOnPreparedListener {
            Handler().postDelayed({
                binding.supBottomSheet.iv_sheet_video_cover.visibility = View.GONE
            }, 250)
        }

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

            binding.supBottomSheet.tv_sheet_track_name.text = it.trackName
            binding.supBottomSheet.tv_sheet_track_description.text = it.longDescription
            loadPreview(it.previewUrl)

            Handler().postDelayed({
                binding.supBottomSheet.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
            }, 150)
        })
    }

    private fun clearList() {
        itunesList.clear()
        binding.rvItunesData.adapter?.notifyDataSetChanged()
    }

    private fun loadPreview(previewUrl: String) {
        binding.supBottomSheet.vv_sheet_preview.apply {
            setVideoURI(Uri.parse(previewUrl))
            start()
        }
    }

    override fun onBackPressed() {
        when {
            binding.supBottomSheet.panelState == SlidingUpPanelLayout.PanelState.EXPANDED -> binding.supBottomSheet.panelState =
                SlidingUpPanelLayout.PanelState.COLLAPSED
            else -> super.onBackPressed()
        }
    }
}
