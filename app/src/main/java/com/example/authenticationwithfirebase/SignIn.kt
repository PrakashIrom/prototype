package com.example.authenticationwithfirebase

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.authenticationwithfirebase.ui.theme.AuthenticationWithFirebaseTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var justify by remember {
        mutableStateOf(false)
    }
    var showErrorDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val auth: FirebaseAuth = Firebase.auth
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            justify = true
                        } else {
                            showErrorDialog = true
                        }
                    }
            }
        ) {
            Text("Sign In")
        }

        if(justify){
           Surface {
               AlertDialog(
                   onDismissRequest = { justify = true },
                   title = { Text("Success") },
                   text = { Text("You successfully signed in") },
                   confirmButton = {
                       Button(
                           onClick = { showErrorDialog = false }
                       ) {
                           Text("OK")
                       }
                   }
               )
           }
        }
        // Show error dialog if authentication fails
        if (showErrorDialog) {
            AlertDialog(
                onDismissRequest = { showErrorDialog = false },
                title = { Text("Error") },
                text = { Text("Invalid username or password") },
                confirmButton = {
                    Button(
                        onClick = { showErrorDialog = false }
                    ) {
                        Text("OK")
                    }
                }
            )
        }

        // Navigate to signup screen
        Text(
            text = "Don't have an account? Create new",
            modifier = Modifier.clickable {
                navController.navigate("create")
            }
        )
    }
}




@Preview(showBackground = true)
@Composable
fun prevScreen(){
    val navController = rememberNavController()
    AuthenticationWithFirebaseTheme{
        LoginScreen(Modifier,navController)
    }
}