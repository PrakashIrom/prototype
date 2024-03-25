package com.example.authenticationwithfirebase.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.authenticationwithfirebase.CreateAccountScreen
import com.example.authenticationwithfirebase.LoginScreen

@Composable
fun navSign(navController: NavHostController){
    NavHost(navController = navController, startDestination = "login"){
        composable("login"){
            LoginScreen(Modifier,navController)
        }

        composable("create"){
            CreateAccountScreen(Modifier,navController)
        }

    }
}