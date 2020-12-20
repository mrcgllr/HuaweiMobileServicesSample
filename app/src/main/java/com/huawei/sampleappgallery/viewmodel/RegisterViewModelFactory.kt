package com.huawei.sampleappgallery.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.huawei.agconnect.auth.AGConnectAuth

class RegisterViewModelFactory(
    private val application: Application,
    private val agConnectAuth: AGConnectAuth,
    private val firebaseAuth: FirebaseAuth
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(application, agConnectAuth,firebaseAuth) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}