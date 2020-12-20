package com.huawei.sampleappgallery.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.huawei.agconnect.auth.AGConnectAuth
import com.huawei.agconnect.auth.EmailAuthProvider
import com.huawei.sampleappgallery.util.Util

class LoginViewModel(
    application: Application,
    private val hmsAuth: AGConnectAuth,
    private val gmsAuth: FirebaseAuth
) : AndroidViewModel(application) {


    private var _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private var _isSuccessLogin = MutableLiveData<Boolean>()
    val isSuccessLogin: LiveData<Boolean> get() = _isSuccessLogin

    fun loginUsers(email: String, password: String) {
        if (Util.isHmsAvailable(getApplication())) {
            val credential = EmailAuthProvider.credentialWithPassword(email, password)
            hmsAuth.signIn(credential).addOnSuccessListener {
                _isSuccessLogin.value = true
                _message.value = "Login is successful"
            }.addOnFailureListener { exception ->
                _isSuccessLogin.value = false
                _message.value = exception.localizedMessage
            }
        } else if (Util.isGmsAvailable(getApplication())) {
            gmsAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                _isSuccessLogin.value = true
                _message.value = "Login is successful"
            }.addOnFailureListener { exception ->
                _isSuccessLogin.value = false
                _message.value = exception.localizedMessage
            }
        }
    }
}