package com.example.jetpack

import SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack.ui.authorization.AuthorizationScreen
import com.example.jetpack.ui.home.HomeScreen
import com.example.jetpack.ui.registration.RegistrationScreen

class Navigation {
    @Composable
    fun NavGraph() {
        val navController = rememberNavController()
            NavHost(navController = navController, startDestination = AUTHORIZATION) {
                composable(AUTHORIZATION) {
                    AuthorizationScreen().ShowAuthorizationContent(
                        onClick = {
                            navController.navigate(REGISTRATION)
                        },
                        onBtnClick = {
                            navController.navigate(SPLASHSCREEN)
                        })
                }
                composable(REGISTRATION) {
                    RegistrationScreen().ShowRegistrationContent(
                        onClick = {
                            navController.navigate(AUTHORIZATION)
                        },
                        onBtnClick = {
                            navController.navigate(SPLASHSCREEN)
                        })
                }
                composable(SPLASHSCREEN){
                    SplashScreen().ShowSplashScreen(){
                        navController.navigate(HOMESCREEN)
                    }
                }
                composable(HOMESCREEN) {
                    HomeScreen().ShowHomeContent() {
                        navController.navigate(AUTHORIZATION)
                    }
                }
            }
        }


    companion object {
        const val AUTHORIZATION = "authorization"
        const val REGISTRATION = "registration"
        const val HOMESCREEN = "home"
        const val SPLASHSCREEN = "splash"
    }
}