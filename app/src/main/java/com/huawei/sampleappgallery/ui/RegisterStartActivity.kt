package com.huawei.sampleappgallery.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.huawei.agconnect.auth.AGConnectAuth
import com.huawei.sampleappgallery.databinding.ActivityRegisterStartBinding
import com.huawei.sampleappgallery.viewmodel.RegisterStartViewModel

class RegisterStartActivity : AppCompatActivity() {
    private lateinit var auth: AGConnectAuth
    private lateinit var viewModel: RegisterStartViewModel
    private lateinit var binding: ActivityRegisterStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStartBinding.inflate(layoutInflater)
        auth = AGConnectAuth.getInstance()
        viewModel = ViewModelProvider(this).get(RegisterStartViewModel::class.java)
        setContentView(binding.root)
        var email = ""

        binding.btnSendVerify.setOnClickListener {
            email = binding.edtMail.text.toString()
            viewModel.hmsSendVerify(email)
        }

        viewModel.isSendVerify.observe(this, { isSend ->
            if (isSend) {
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
            }
        })
        viewModel.toastMessage.observe(this, { message ->
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        })
    }
}