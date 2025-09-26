package com.revzion.cognivia.core.di

import com.revzion.cognivia.feature.HomeBase.presentation.viewmodels.CourseScreenViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val targetModule: Module

val sharedModule= module{
    viewModel { CourseScreenViewModel() }
}

fun initializeKoin(
    config: (KoinApplication.()-> Unit)?=null
){
    startKoin {
        config?.invoke(this)
        modules(
            listOf(
                targetModule,
                sharedModule
            )
        )
    }
}