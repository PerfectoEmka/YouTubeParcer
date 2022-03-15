package com.example.kotlinl5.presentation.ui.activities.playlist

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinl5.core.ui.base.BaseActivity
import com.example.kotlinl5.data.local.AppPrefs
import com.example.kotlinl5.databinding.ActivityPlaylistBinding
import com.example.kotlinl5.domain.models.Items
import com.example.kotlinl5.utils.extensions.visibility
import com.example.kotlinl5.presentation.ui.activities.details.DetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayListActivity : BaseActivity<PlayListViewModel, ActivityPlaylistBinding>(){

    private var list = mutableListOf<Items>()
    private val adapter: PlayListAdapter by lazy {
        PlayListAdapter(list)
    }

    override val viewModel: PlayListViewModel by viewModel()

    override fun initView() {
        super.initView()
        //viewModel = ViewModelProvider(this).get(PlayListViewModel::class.java)
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

        adapter.onItemClickListener = {
            startDetailActivity(it)
        }
    }

    override  fun initViewModel() {
        super.initViewModel()
        viewModel.getPlayList()
        viewModel.loading.observe(this){
            binding.progressBarContainer.progressBar.isVisible = it
        }
        viewModel.playlist.observe(this) {
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

    private fun startDetailActivity(it: Items) {
        Intent(this, DetailsActivity::class.java).apply {
            putExtra("playlistId", it.id)
            putExtra("playlistTitle", it.snippet.title)
            putExtra("playlistDescription", it.snippet.description)
            startActivity(this)
        }
    }

    private fun checkInternetConnection() {
        if (isOnline()){
            viewModel.getPlayList()
            binding.connectionLayout.mainContainer.visibility(false)
            viewModel.loading.observe(this){
                binding.progressBarContainer.progressBar.isVisible = it
            }
            viewModel.playlist.observe(this) {
                initAdapter(it.items as MutableList<Items>)
            }
        } else binding.connectionLayout.mainContainer.visibility(true)
    }

    override fun inflateVB(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }
}
