package com.example.kotlinl5.utils.di

import com.example.kotlinl5.core.network.networkModule
import com.example.kotlinl5.data.local.prefsModule
import com.example.kotlinl5.data.remote.remoteDataSource

val koinModules = listOf(
    repoModules,
    viewModules,
    remoteDataSource,
    networkModule,
    prefsModule
)