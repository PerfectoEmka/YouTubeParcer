package com.example.kotlinl5.ui.details

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinhm2.extensions.visibility
import com.example.kotlinl5.ui.player.PlayerActivity
import com.example.kotlinl5.R
import com.example.kotlinl5.core.network.result.Status
import com.example.kotlinl5.core.ui.base.BaseActivity
import com.example.kotlinl5.data.remote.model.Items
import com.example.kotlinl5.databinding.ActivityDetailsBinding


class DetailsActivity : BaseActivity<DetailsViewModel, ActivityDetailsBinding>(){

    private var items = mutableListOf<Items>()
    private val adapter: DetailsAdapter by lazy {
        DetailsAdapter(items, this::onItemClickListener)
    }

    override fun initView() {
        super.initView()

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        playlistId = intent.getStringExtra("playlistId").toString()
        checkInternetConnection()

        binding.tvTitle.text = intent.getStringExtra("playlistTitle").toString()
        binding.tvDescription.text = intent.getStringExtra("playlistDescription").toString()

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
    }

    private lateinit var playlistId: String

    override fun inflateVB(inflater: LayoutInflater): ActivityDetailsBinding {
        return ActivityDetailsBinding.inflate(layoutInflater)
    }

    override fun initListener() {
        super.initListener()

        binding.fab.setOnClickListener {
            Intent(this, PlayerActivity::class.java).apply {
                putExtra("videoId", items[0].contentDetails.videoId)
                startActivity(this)
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.connectionLayout.btnTryAgain.setOnClickListener {
            checkInternetConnection()
        }
    }

    private fun onItemClickListener(item: Items){
        Intent(this, PlayerActivity::class.java).apply {
            putExtra("videoId", item.contentDetails.videoId)
            startActivity(this)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading.observe(this){
            binding.progressBarContainer.progressBar.isVisible = it
        }
        viewModel.getPlaylistItems(playlistId).observe(this) {
            when (it.status){
                Status.LOADING -> viewModel.loading.postValue(true)
                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    initAdapter(it.data?.items as MutableList<Items>)
                    binding.contentScrolling.tvVideoCount?.text = it.data.items.size.toString()+
                            " " + getString(R.string.video_series)
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                }
            }
        }
    }

    private fun initAdapter(items: MutableList<Items>) {
        this.items = items
        binding.contentScrolling.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@DetailsActivity.adapter
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkInternetConnection() {
        if (isOnline()){
            binding.connectionLayout.mainContainer.visibility(false)
            binding.fab.visibility(true)

            viewModel.loading.observe(this){
                binding.progressBarContainer.progressBar.isVisible = it
            }

            viewModel.getPlaylistItems(playlistId).observe(this) {
                when (it.status){
                    Status.LOADING -> viewModel.loading.postValue(true)
                    Status.SUCCESS -> {
                        viewModel.loading.postValue(false)
                        initAdapter(it.data?.items as MutableList<Items>)
                        binding.contentScrolling.tvVideoCount?.text = it.data
                            .items
                            .size
                            .toString()+ " " + getString(R.string.video_series)
                    }
                    Status.ERROR -> {
                        viewModel.loading.postValue(false)
                    }
                }
            }
        } else {
            binding.connectionLayout.mainContainer.visibility(true)
            binding.fab.visibility(false)
        }
    }
}