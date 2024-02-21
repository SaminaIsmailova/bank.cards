package com.example.jetpack.ui.registration

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import com.example.jetpack.model.Users
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.database

class RegistrationViewModel:ViewModel() {
    private val auth=FirebaseAuth.getInstance()
    private val database=Firebase.database.getReference(DATABASEREFERENCE)

    fun saveDataInDatabase(fname:String,sName:String,email:String,password:String) {
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser
                    val uid = user?.uid
                    if (uid != null) {
                        val users = Users(uid, fname, sName, email, password)
                        database.child(uid).setValue(users)
                    }

                }
            }
        }
    }
    companion object{
        const val DATABASEREFERENCE="Users"
    }
}