package com.example.kotlinl5.ui.playlist

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinhm2.extensions.visibility
import com.example.kotlinl5.core.ui.base.BaseActivity
import com.example.kotlinl5.data.local.AppPrefs
import com.example.kotlinl5.databinding.ActivityPlaylistBinding
import com.example.kotlinl5.data.remote.model.Items
import com.example.kotlinl5.ui.details.DetailsActivity

class PlayListActivity : BaseActivity<PlayListViewModel, ActivityPlaylistBinding>(){

    private var list = mutableListOf<Items>()
    private val adapter: PlayListAdapter by lazy {
        PlayListAdapter(list, this::onItemClickListener)
    }

    override fun initView() {
        super.initView()
        viewModel = ViewModelProvider(this).get(PlayListViewModel::class.java)
        checkInternetConnection()

        Log.d("appPrefs", AppPrefs(this).isOnBoard.toString())
        //showToast(AppPrefs(this).isOnBoard.toString())
        AppPrefs(this).isOnBoard = true
    }

    override fun initListener() {
        super.initListener()
        binding.connectionLayout.btnTryAgain.setOnClickListener {
            checkInternetConnection()
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getPlayList().observe(this) {
            Toast.makeText(this, it.kind, Toast.LENGTH_SHORT).show()
            initAdapter(it.items as MutableList<Items>)
        }
    }

    private fun initAdapter(list: MutableList<Items>) {
        this.list = list
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PlayListActivity.adapter
        }
    }

    private fun onItemClickListener(item: Items){
        Intent(this, DetailsActivity::class.java).apply {
            putExtra("playlistId", item.id)
            putExtra("playlistTitle", item.snippet.title)
            putExtra("playlistDescription", item.snippet.description)
            startActivity(this)
        }
    }

    override fun inflateVB(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    private fun checkInternetConnection() {
        if (isOnline()){
            binding.mainContainer.visibility(false)
            viewModel.getPlayList().observe(this) {
                initAdapter(it.items as MutableList<Items>)
            }
        } else binding.mainContainer.visibility(true)
    }
}