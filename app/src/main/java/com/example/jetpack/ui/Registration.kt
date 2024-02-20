package com.example.jetpack.ui

import android.text.TextUtils
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.database

class Registration {
    private val auth = FirebaseAuth.getInstance()
    private val myDataBase = Firebase.database.getReference(DATABASENAME)

    @Preview(showSystemUi = true)
    @Composable
    fun RegistrationScreen(onClick: () -> Unit = {}, onBtnClick: () -> Unit = {}) {

        Column(
            modifier = Modifier
                .padding(top = Dimensions.Padding.padding_20)
        )
        {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.registration),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = Dimensions.TextSize.size_20
            )
            RegistrationUsers(
                onClick = onClick,
               onBtnClick =  onBtnClick
            )
        }
    }

    @Composable
    fun RegistrationUsers(onClick: () -> Unit, onBtnClick: () -> Unit) {
        var fName by remember {
            mutableStateOf("")
        }
        var sName by remember {
            mutableStateOf("")
        }
        var eMail by remember {
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
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimensions.Padding.padding_20)
                    .clip(RoundedCornerShape(Dimensions.Size.size_16))
                    .border(
                        1.dp,
                        color = colorResource(id = R.color.black),
                        RoundedCornerShape(Dimensions.Size.size_16)
                    ),
                value = fName,
                onValueChange = { fName = it },
                placeholder = {
                    Text(text = stringResource(R.string.surname))
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimensions.Padding.padding_20)
                    .clip(RoundedCornerShape(Dimensions.Size.size_16))
                    .border(
                        1.dp,
                        color = colorResource(id = R.color.black),
                        RoundedCornerShape(Dimensions.Size.size_16)
                    ),
                value = sName,
                onValueChange = { sName = it },
                placeholder = {
                    Text(text = stringResource(R.string.name))
                },

                )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimensions.Padding.padding_20)
                    .clip(RoundedCornerShape(Dimensions.Size.size_16))
                    .border(
                        1.dp,
                        color = colorResource(id = R.color.black),
                        RoundedCornerShape(Dimensions.Size.size_16)
                    ),
                value = eMail,
                onValueChange = { eMail = it },
                placeholder = {
                    Text(text = stringResource(id = R.string.email))
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimensions.Padding.padding_20)
                    .clip(RoundedCornerShape(Dimensions.Size.size_16))
                    .border(
                        1.dp,
                        color = colorResource(id = R.color.black),
                        RoundedCornerShape(Dimensions.Size.size_16)
                    ),
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(text = stringResource(id = R.string.password))
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(painter = icon, contentDescription = null)
                    }
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation()

            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimensions.Padding.padding_20),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red)),
                onClick = {
                    if (!TextUtils.isEmpty(eMail) && !TextUtils.isEmpty(password)) {
                        auth.createUserWithEmailAndPassword(eMail, password).addOnCompleteListener {
                            if (it.isSuccessful) {
                                val user: FirebaseUser? = auth.currentUser
                                val uid = user?.uid
                                if (uid != null) {
                                    val users = Users(uid, fName, sName, eMail, password)
                                    myDataBase.child(uid).setValue(users)
                                }
                                onBtnClick()
                            }
                        }
                    }
                }) {
                Text(
                    modifier = Modifier.padding(Dimensions.Padding.padding_14),
                    text = stringResource(id = R.string.register),
                    fontSize = Dimensions.TextSize.size_20,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.Center, verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.have_an_account),
                    fontSize = Dimensions.TextSize.size_16,
                    color = colorResource(
                        id = R.color.black
                    )
                )
                Text(
                    text = stringResource(id = R.string.sign_in),
                    fontSize = Dimensions.TextSize.size_18,
                    color = colorResource(
                        id = R.color.brown
                    ),
                    modifier = Modifier.clickable {
                        onClick()
                    },
                    fontWeight = FontWeight.Bold

                )
            }
        }
    }

    companion object {
        const val DATABASENAME = "Users"
    }
}