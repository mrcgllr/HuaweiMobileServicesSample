package com.huawei.sampleappgallery.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.huawei.agconnect.auth.AGConnectAuth
import com.huawei.agconnect.auth.EmailUser
import com.huawei.sampleappgallery.util.Util
import kotlinx.coroutines.launch

class RegisterViewModel(
    application: Application,
    private val hmsAuth: AGConnectAuth,
    private val gmsAuth: FirebaseAuth
) : AndroidViewModel(application) {

    private var _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private var _isSuccessRegister = MutableLiveData<Boolean>()
    val isSuccessRegister: LiveData<Boolean> get() = _isSuccessRegister

    fun register(email: String, password: String, verifyCode: String?) = viewModelScope.launch {
        if (Util.isHmsAvailable(getApplication())) {
            hmsRegister(email, password, verifyCode!!)
        } else if (Util.isGmsAvailable(getApplication())) {
            gmsRegisterUser(email, password)
        }

    }

    private fun gmsRegisterUser(eMail: String, password: String) {
            gmsAuth.createUserWithEmailAndPassword(eMail.trim(), password)
                .addOnSuccessListener { it ->
                    _isSuccessRegister.value = true
                    _message.value = it.user?.uid
                }.addOnFailureListener { exception ->
                    _isSuccessRegister.value = false
                    _message.value = exception.localizedMessage
                }

        }

    private fun hmsRegister(email: String, password: String, verifyCode: String){
        hmsAuth.createUser(
            EmailUser.Builder().setEmail(email).setVerifyCode(verifyCode).setPassword(password)
                .build()
        )
            .addOnSuccessListener { result ->
                _isSuccessRegister.value = true
                _message.value = result.user.uid
            }.addOnFailureListener { exception ->
                _isSuccessRegister.value = false
                _message.value = exception.localizedMessage
            }
    }
}