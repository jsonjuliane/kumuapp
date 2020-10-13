package wanda.weiss.kumuchallenge.view.itunes

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itunesList[position]
        binding.apply {
            tvItunesTrackName.text = item.trackName
            tvItunesTrackPrice.text = "PHP${item.trackPrice}"
            tvItunesTrackGenre.text = item.primaryGenreName
        }
    }
}