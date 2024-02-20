package com.example.jetpack.ui

import android.text.TextUtils
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack.Dimensions
import com.example.jetpack.R
import com.example.jetpack.model.Users
import com.example.jetpack.ui.Registration.Companion.DATABASENAME
import com.example.jetpack.ui.theme.JetPackTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class Authorization {
    private val auth = FirebaseAuth.getInstance()
    private val myDataBase=Firebase.database.getReference(DATABASENAME)


    @Composable
    @Preview(showSystemUi = true)
    fun AuthorizationScreen(onClick: () -> Unit = {}, onBtnClick: () -> Unit = {}) {
        JetPackTheme {
            Column {
                Text(
                    text = stringResource(R.string.entrance),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Dimensions.Padding.padding_28),
                    fontSize = Dimensions.TextSize.size_20,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Logins(onClick, onBtnClick)
            }
        }
    }

    @Composable
    fun Logins(onClick: () -> Unit, onBtnClick: () -> Unit) {

        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var passwordVisibility by remember {
            mutableStateOf(false)
        }

        val icon =
            if (passwordVisibility)
                painterResource(id = R.drawable.ic_toggle_password_visibility_off)
            else
                painterResource(id = R.drawable.ic_toggle_password_visibility)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimensions.Padding.padding_16)
                    .border(
                        1.dp,
                        color = colorResource(id = R.color.black),
                        RoundedCornerShape(size = Dimensions.Size.size_16)
                    )
                    .clip(RoundedCornerShape(Dimensions.Size.size_16)),
                placeholder = {
                    Text(text = stringResource(R.string.email))
                },
                value = email,
                onValueChange = { email = it })
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimensions.Padding.padding_20)
                    .clip(RoundedCornerShape(Dimensions.Size.size_16))
                    .border(
                        1.dp,
                        color = colorResource(id = R.color.black),
                        shape = RoundedCornerShape(Dimensions.Size.size_16)
                    ),
                placeholder = {
                    Text(text = stringResource(R.string.password))
                },
                value = password,
                onValueChange = { password = it },
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(painter = icon, contentDescription = null)
                    }
                },
                visualTransformation = if (passwordVisibility)
                    VisualTransformation.None
                else PasswordVisualTransformation()

            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Dimensions.Size.size_16))
                    .padding(
                        vertical = Dimensions.Padding.padding_20,
                        horizontal = Dimensions.Padding.padding_20
                    )
                    .height(Dimensions.Size.size_60),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                onClick = {
                    if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                            if (it.isSuccessful) {
                                onBtnClick()
                            }
                        }
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.sign_in),
                    fontSize = Dimensions.TextSize.size_20,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimensions.Padding.padding_20),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.dont_have_account),
                    fontSize = Dimensions.TextSize.size_16,
                    color = colorResource(id = R.color.black)
                )
                Text(
                    modifier = Modifier.clickable {
                        onClick()
                    },
                    text = stringResource(R.string.register),
                    fontSize = Dimensions.TextSize.size_16,
                    color = colorResource(id = R.color.brown),
                    fontWeight = FontWeight.Bold

                )
            }
        }
    }


}