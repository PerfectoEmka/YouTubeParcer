package com.example.kotlinl5.utils.di

import com.example.kotlinl5.core.ui.base.BaseViewModel
import com.example.kotlinl5.presentation.ui.activities.details.DetailsViewModel
import com.example.kotlinl5.presentation.ui.activities.player.PlayerViewModel
import com.example.kotlinl5.presentation.ui.activities.playlist.PlayListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { PlayListViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { PlayerViewModel(get()) }
    viewModel { BaseViewModel() }
}