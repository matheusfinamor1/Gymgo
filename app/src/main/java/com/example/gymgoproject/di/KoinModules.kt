package com.example.gymgoproject.di

import com.example.gymgoproject.authentication.FirebaseAuthRepository
import com.example.gymgoproject.repositories.UsersRepository
import com.example.gymgoproject.ui.viewmodels.SignInViewModel
import com.example.gymgoproject.ui.viewmodels.SignUpViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf

val appModule = module {
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)

}

val storageModule = module {
    singleOf(::UsersRepository)
    singleOf(::FirebaseAuthRepository)
}

val firebaseModule = module {
    single {
        Firebase.auth
    }
}