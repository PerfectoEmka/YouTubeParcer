package com.example.kotlinl5.core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.kotlinl5.InternetConnection

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    protected abstract fun inflateVB(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateVB(layoutInflater)
        setContentView(binding.root)

        isOnline()
        initView()
        initViewModel()
        initListener()
    }

    open fun isOnline(): Boolean {
        return InternetConnection().isOnline(applicationContext)
    }

    open fun initViewModel() {}
    open fun initListener() {}
    open fun initView() {}

}