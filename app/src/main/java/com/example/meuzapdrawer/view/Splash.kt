package com.example.meuzapdrawer.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser

        val intent:Intent = if (user == null){
            Intent(this, LogInActivity::class.java)
        }else{
            Intent(this, MainActivity::class.java)
        }
        startActivity(intent)
        finish()
    }


}