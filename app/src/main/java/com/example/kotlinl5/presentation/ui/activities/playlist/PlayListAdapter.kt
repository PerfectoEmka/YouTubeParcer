package com.example.kotlinl5.presentation.ui.activities.playlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinl5.R
import com.example.kotlinl5.databinding.ItemPlaylistBinding
import com.example.kotlinl5.domain.models.Items
import com.example.kotlinl5.utils.extensions.load

class PlayListAdapter(private val list: MutableList<Items>)
: RecyclerView.Adapter<PlayListAdapter.ViewHolder>() {

    var onItemClickListener: ((Items) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val binding: ItemPlaylistBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(item: Items) {
            binding.tvTitle.text = item.snippet.title
            binding.tvCaptureCount.text = item.contentDetails.itemCount.toString() + itemView.context.getString(R.string.video_series)
            binding.ivPlaylistBanner.load(item.snippet.thumbnails.medium.url)
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}