package wanda.weiss.kumuchallenge.view.itunes

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber
import wanda.weiss.kumuchallenge.R
import wanda.weiss.kumuchallenge.databinding.ItemItunesBinding
import wanda.weiss.kumuchallenge.model.pojo.Result
import wanda.weiss.kumuchallenge.view.BaseRecyclerAdapter

class ItunesAdapter(private var itunesList: ArrayList<Result>) :
    BaseRecyclerAdapter<ItemItunesBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return bind(parent, R.layout.item_itunes)
    }

    override fun getItemCount(): Int {
        return itunesList.size
    }

    override fun getItemId(position: Int): Long {
        return System.currentTimeMillis()
    }

    override fun getItemViewType(position: Int): Int {
        return System.currentTimeMillis().toInt()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itunesList[position]
        binding.tvItunesTrackName.text = item.trackName
        binding.tvItunesTrackPrice.text = "PHP${item.trackPrice}"
        binding.tvItunesTrackGenre.text = item.primaryGenreName
    }
}