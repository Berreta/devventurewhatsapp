package com.example.meuzapdrawer.repository

import com.example.meuzapdrawer.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object UserRepository {
    //firestore
    private val db by lazy { Firebase.firestore }
    fun addUser(user: User, onSuccess: () -> Unit, onFail: (error: String) -> Unit){
        db.collection("users") //email chave primaria
            .document(user.email)
            .set(user)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFail(e.localizedMessage)
            }
    }
}

//usa no login