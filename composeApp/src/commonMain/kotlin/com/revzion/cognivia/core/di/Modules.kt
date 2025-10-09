package com.revzion.cognivia.core.di

import com.revzion.cognivia.auth.AuthRepository
import com.revzion.cognivia.auth.AuthViewModel
import com.revzion.cognivia.feature.homeBase.presentation.viewmodels.CourseScreenViewModel
import com.revzion.cognivia.feature.homeBase.presentation.viewmodels.SectionScreenViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val targetModule: Module

val sharedModule= module{
    viewModel { CourseScreenViewModel() }
    viewModel { SectionScreenViewModel() }

    single { AuthRepository() }
    viewModel { AuthViewModel(get()) }
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