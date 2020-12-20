package com.huawei.sampleappgallery.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.huawei.agconnect.auth.AGConnectAuth
import com.huawei.sampleappgallery.databinding.ActivityLoginBinding
import com.huawei.sampleappgallery.util.Util
import com.huawei.sampleappgallery.viewmodel.LoginViewModel
import com.huawei.sampleappgallery.viewmodel.LoginViewModelFactory


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var hmsAuth: AGConnectAuth
    private lateinit var gmsAuth: FirebaseAuth
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        hmsAuth = AGConnectAuth.getInstance()
        gmsAuth = FirebaseAuth.getInstance()
        viewModelFactory = LoginViewModelFactory(application, hmsAuth, gmsAuth)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        setContentView(binding.root)
        hmsAuth.signOut()
        binding.btnLogin.setOnClickListener {
            val email = binding.edtMail.text.toString()
            val password = binding.edtPassword.text.toString()
            viewModel.loginUsers(email, password)
        }

        binding.txtRegister.setOnClickListener {
            if (Util.isHmsAvailable(this)) {
                val intent = Intent(this, RegisterStartActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }

        viewModel.isSuccessLogin.observe(this, { isSend ->
            if (isSend) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

        viewModel.message.observe(this, { message ->
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        })
    }
}