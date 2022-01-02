package com.example.kotlinl5.ui.playlist

import android.content.Intent
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinhm2.extensions.visibility
import com.example.kotlinl5.base.BaseActivity
import com.example.kotlinl5.databinding.ActivityPlaylistBinding
import com.example.kotlinl5.databinding.NoInternetConnectionBinding
import com.example.kotlinl5.model.Items
import com.example.kotlinl5.ui.new_activity.NewActivity

class PlayListActivity : BaseActivity<PlayListViewModel, ActivityPlaylistBinding>(){

    private lateinit var adapter: PlayListAdapter
    private lateinit var internetBiding: NoInternetConnectionBinding

    override fun initView() {
        super.initView()
        viewModel = ViewModelProvider(this).get(PlayListViewModel::class.java)
        internetBiding = NoInternetConnectionBinding.inflate(layoutInflater)
        checkInternetConnection()
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
            initAdapter(it.items)
        }
    }

    private fun initAdapter(items: List<Items>) {
        adapter = PlayListAdapter(items)
        adapter.onItemClick = {
            val intent = Intent(this, NewActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, it.id)
            }
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
    }

    override fun inflateVB(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    private fun checkInternetConnection() {
        if (isOnline(this)){
            binding.mainContainer.visibility(false)
            initViewModel()
        } else binding.mainContainer.visibility(true)
    }
}