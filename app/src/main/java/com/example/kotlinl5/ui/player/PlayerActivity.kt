package com.example.kotlinl5.ui.player

import android.view.LayoutInflater
import com.example.kotlinl5.extensions.showToast
import com.example.kotlinl5.core.ui.base.BaseActivity
import com.example.kotlinl5.core.ui.base.BaseViewModel
import com.example.kotlinl5.databinding.ActivityPlayerBinding

class PlayerActivity : BaseActivity<BaseViewModel, ActivityPlayerBinding>() {

    override fun initView() {
        super.initView()
        val id = intent.getStringExtra("videoId")
        showToast(id.toString())
    }

    override fun inflateVB(inflater: LayoutInflater): ActivityPlayerBinding {
        return ActivityPlayerBinding.inflate(layoutInflater)
    }
}