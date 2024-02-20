package com.example.jetpack

import SplashScreen
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack.ui.Authorization
import com.example.jetpack.ui.Home
import com.example.jetpack.ui.Registration

class Navigation {
    @Composable
    fun NavGraph() {
        val navController = rememberNavController()
            NavHost(navController = navController, startDestination = AUTHORIZATION) {
                composable(AUTHORIZATION) {
                    Authorization().AuthorizationScreen(
                        onClick = {
                            navController.navigate(REGISTRATION)
                        },
                        onBtnClick = {
                            navController.navigate(SPLASHSCREEN)
                        })
                }
                composable(REGISTRATION) {
                    Registration().RegistrationScreen(
                        onClick = {
                            navController.navigate(AUTHORIZATION)
                        },
                        onBtnClick = {
                            navController.navigate(SPLASHSCREEN)
                        })
                }
                composable(SPLASHSCREEN){
                    SplashScreen().Splash(){
                        navController.navigate(HOMESCREEN)
                    }
                }
                composable(HOMESCREEN) {
                    Home().HomeScreen() {
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