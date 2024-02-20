package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jetpack.ui.Authorization
import com.example.jetpack.ui.theme.JetPackTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackTheme {
                Navigation().NavGraph()
            }
        }
    }
}



