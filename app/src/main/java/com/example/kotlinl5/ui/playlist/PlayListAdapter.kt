package com.example.kotlinl5.ui.playlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinhm2.extensions.load
import com.example.kotlinl5.R
import com.example.kotlinl5.databinding.ItemPlaylistBinding
import com.example.kotlinl5.model.Items

class PlayListAdapter(private val list: List<Items>): RecyclerView.Adapter<PlayListAdapter.ViewHolder>() {

    var onItemClick: ((Items) -> Unit)? = null

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
            binding.tvCaptureCount.text =
                item.contentDetails.itemCount.toString() + itemView.context.getString(R.string.video_series)
            binding.ivPlaylistBanner.load(item.snippet.thumbnails.default.url)
            binding.root.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }
}