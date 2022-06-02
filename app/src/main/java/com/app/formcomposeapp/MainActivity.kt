package com.app.formcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.formcomposeapp.ui.theme.FormComposeAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginForm()
                }
            }
        }
    }
}

@Composable
fun LoginForm() {
    val scaffoldState = rememberScaffoldState()
    var emailTextFieldState by remember {
        mutableStateOf("")
    }
    var passwordTextFieldState by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = emailTextFieldState,
                singleLine = true,
                onValueChange = {
                    emailTextFieldState = it
                },
                label = {
                    Text(text = "Email address")
                })

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordTextFieldState,
                singleLine = true,
                onValueChange = {
                    passwordTextFieldState = it
                },
                label = {
                    Text(text = "Password")
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Email $emailTextFieldState\nPassword $passwordTextFieldState")
                    }
                },
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = 25.dp,
                        top = 8.dp,
                        end = 25.dp,
                        bottom = 8.dp
                    ),
                    text = "Login", fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FormComposeAppTheme {
        LoginForm()
    }

}