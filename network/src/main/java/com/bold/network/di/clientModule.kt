package com.bold.network.di

import com.bold.network.NetworkModule
import org.koin.dsl.module

val clientModule = module {
    single {
        NetworkModule.client
    }
}