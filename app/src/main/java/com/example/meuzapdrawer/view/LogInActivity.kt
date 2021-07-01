package com.example.meuzapdrawer.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.meuzapdrawer.R
import com.example.meuzapdrawer.model.User
import com.example.meuzapdrawer.repository.UserRepository
import com.example.meuzapdrawer.viewmodel.LogInViewModel
import com.example.meuzapdrawer.viewmodel.LoginViewModelFactory
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private val resultLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        activityResult(result.resultCode, result.data)
    }
    private val LogInModel: LogInViewModel by viewModels {
        LoginViewModelFactory(
            UserRepository
        )
    }

    //private val LogInModel: LogInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        findViewById<Button>(R.id.btnLogin).setOnClickListener {

            resultLaucher.launch(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build()
            )
        }
    }

    private fun activityResult(resultCode: Int, data: Intent?) {

            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                //se tiver logado o cara tem um valor
                val current = FirebaseAuth.getInstance().currentUser?.apply {
                    val user: User = User(name = this.displayName ?: "Sem Nome",
                    email = this.email ?: "Sem Email", id = this.uid)
                    UserRepository.addUser(user, {
                        goToMain()
                    }, {
                        failToLogin(it)
                    })
                }
            } else {
               failToLogin(response?.error?.message)
            }
        }

    private fun failToLogin(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun goToMain() {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

