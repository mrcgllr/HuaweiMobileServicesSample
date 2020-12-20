package com.huawei.sampleappgallery.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.huawei.agconnect.auth.AGConnectAuth

class LoginViewModelFactory(
    private val application: Application,
    private val hmsAuth: AGConnectAuth,
    private val gmsAuth: FirebaseAuth
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(application, hmsAuth,gmsAuth) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}