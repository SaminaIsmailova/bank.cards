package com.example.jetpack.ui.authorization

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthorizationViewModel : ViewModel() {

    private val auth=FirebaseAuth.getInstance()
    fun performLogin(eMail: String, password: String, onClick: () -> Unit) {
        if (eMail.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(eMail,password).addOnCompleteListener {
                if(it.isSuccessful){
                    onClick()
                }
            }
        }
    }

}