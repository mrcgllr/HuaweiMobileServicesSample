package com.huawei.sampleappgallery.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.huawei.agconnect.auth.AGConnectAuth
import com.huawei.sampleappgallery.databinding.ActivityLoginBinding
import com.huawei.sampleappgallery.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: AGConnectAuth
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        auth = AGConnectAuth.getInstance()
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        setContentView(binding.root)
        var email=""
        binding.btnSendVerify.setOnClickListener {
            email = binding.edtMail.text.toString()
            viewModel.hmsSendVerify(email)
        }
        viewModel.isSendVerify.observe(this, { isSend ->
            if (isSend) {
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra("email",email)
                startActivity(intent)
            }
        })
        viewModel.toastMessage.observe(this,{message->
            Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
        })
    }
}