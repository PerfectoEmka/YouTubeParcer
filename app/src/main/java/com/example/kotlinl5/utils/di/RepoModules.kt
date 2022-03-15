package com.example.kotlinl5.utils.di

import com.example.kotlinl5.data.repository.YouTubeRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module {
    single { YouTubeRepositoryImpl(get()) }
    //single { BlablaRepository() }
}