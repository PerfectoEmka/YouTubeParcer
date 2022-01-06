package com.example.kotlinl5

import android.app.Application
import com.example.kotlinl5.core.network.RetrofitClient
import com.example.kotlinl5.repository.Repository

class App: Application() {
    val repository by lazy { Repository()}
    val youTubeApi by lazy { RetrofitClient.create() }
}