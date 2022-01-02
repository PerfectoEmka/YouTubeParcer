package com.example.kotlinl5.ui.new_activity

import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import com.example.kotlinhm2.extensions.showToast
import com.example.kotlinl5.base.BaseActivity
import com.example.kotlinl5.base.BaseViewModel
import com.example.kotlinl5.databinding.ActivityNewBinding

class NewActivity : BaseActivity<BaseViewModel, ActivityNewBinding>(){

    override fun inflateVB(inflater: LayoutInflater): ActivityNewBinding {
        return ActivityNewBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        showToast(message.toString())
    }
}