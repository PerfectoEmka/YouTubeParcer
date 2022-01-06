package com.example.kotlinl5.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinl5.extensions.load
import com.example.kotlinl5.data.remote.model.Items
import com.example.kotlinl5.databinding.ItemVideoBinding

class DetailsAdapter(private val list: MutableList<Items>, private val onItemClickListener: (item: Items) -> Unit)
    : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemVideoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(items: Items) {
            binding.root.setOnClickListener {
                onItemClickListener(items)
            }
            binding.tvTitle.text = items.snippet.title
            binding.tvDuration.text = items.contentDetails.duration
            binding.ivPlaylistBanner.load(items.snippet.thumbnails.medium.url)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}