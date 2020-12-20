package com.huawei.sampleappgallery.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.huawei.agconnect.auth.AGConnectAuth
import com.huawei.sampleappgallery.databinding.ActivityRegisterBinding
import com.huawei.sampleappgallery.viewmodel.RegisterViewModel
import com.huawei.sampleappgallery.viewmodel.RegisterViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var hmsAuth: AGConnectAuth
    private lateinit var gmsAuth: FirebaseAuth
    private lateinit var viewModel: RegisterViewModel
    private lateinit var viewModelFactory: RegisterViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hmsAuth = AGConnectAuth.getInstance()
        gmsAuth = FirebaseAuth.getInstance()
        viewModelFactory = RegisterViewModelFactory(application, hmsAuth, gmsAuth)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
        val email = intent.getStringExtra("email")
        binding.edtMail.setText(email)
        val password = binding.edtPassword.text.toString()
        val verifyCode = binding.edtVerifyCode.text.toString()
        binding.btnRegister.setOnClickListener {
            viewModel.register(
                email!!,
                password,
                verifyCode
            )
        }

        viewModel.message.observe(this, { message ->
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        })
    }
}

