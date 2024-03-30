package com.example.midterm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.midterm.ui.theme.MidtermTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MidtermTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val navController = rememberNavController()
    val bg : Painter = painterResource(id = R.drawable.bg)
    Box(modifier =  Modifier.fillMaxWidth()) {
        Image(painter = bg, contentDescription = null, modifier = Modifier.fillMaxWidth())
    }
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onSignUpClick = { navController.navigate("signup") }
            )
        }
        composable("signup") {
            SignupScreen(
                onSignInClick = { navController.popBackStack() }
            )
        }
    }
}
@Composable
fun LoginScreen(onSignUpClick: () -> Unit){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()

    ) {
        SpeechBubble(text1 = "WELCOME", text2= "Sign In to continue")
        Spacer(modifier = Modifier.height(100.dp))
        TextField(label = "Type your email")
        Spacer(modifier = Modifier.height(20.dp))
        PasswordField(label = "Type your password")
        Spacer(modifier = Modifier.height(60.dp))
        LoginButton()
        Spacer(modifier = Modifier.height(22.dp))
        val textOne = "Not registered yet? "
        val textTwo = "Sign up!"
        val textString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Yellow)){
                pushStringAnnotation(tag = textOne , annotation = textOne)
                append(textOne)
            }
            withStyle(style = SpanStyle(color = Color.Red)){
                pushStringAnnotation(tag = textTwo , annotation = textTwo)
                append(textTwo)
            }
        }
        ClickableText(text = textString , onClick = {
            onSignUpClick()
        })
    }
}
@Composable
fun SignupScreen(onSignInClick: () -> Unit){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()

    ) {
        SpeechBubble(text1 = "WELCOME", text2= "Sign Up to continue")
        Spacer(modifier = Modifier.height(90.dp))
        TextField(label = "Type your email")
        Spacer(modifier = Modifier.height(20.dp))
        PasswordField(label = "Type your password")
        Spacer(modifier = Modifier.height(20.dp))
        RePasswordField(label = "Retype your password")
        Spacer(modifier = Modifier.height(60.dp))
        SignupButton()
        Spacer(modifier = Modifier.height(22.dp))
        val textOne = "Already Registered? "
        val textTwo = "Sign in!"
        val textString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Yellow)){
                pushStringAnnotation(tag = textOne , annotation = textOne)
                append(textOne)
            }
            withStyle(style = SpanStyle(color = Color.Red)){
                pushStringAnnotation(tag = textTwo , annotation = textTwo)
                append(textTwo)
            }
        }
        ClickableText(text = textString , onClick = {
            onSignInClick()
        })
    }
}
@Composable
fun SpeechBubble(text1: String, text2:String) {
    Spacer(modifier = Modifier.height(60.dp))
    Text(
            text= text1,
            color = Color.Yellow,
            style = TextStyle(
                fontSize = 45.sp, // Three times bigger than default size
                fontWeight = FontWeight.ExtraBold
            )

        )
    Spacer(modifier = Modifier.height(25.dp))
    Text(
        text= text2,
        color = Color.Yellow,
        style = TextStyle(
        fontSize = 27.sp, // Three times bigger than default size
        fontWeight = FontWeight.ExtraBold
        )
    )
}
@Composable
fun TextField(label: String) {
    var name by remember { mutableStateOf("") }
    OutlinedTextField(
        value = name,
        onValueChange = { newValue -> name = newValue },
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun PasswordField(label: String) {
    var password by remember { mutableStateOf("") }
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        ),
        shape = RoundedCornerShape(3.dp),
        visualTransformation = PasswordVisualTransformation()
    )
}
@Composable
fun RePasswordField(label: String) {
    var password by remember { mutableStateOf("") }
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(text = label) },
        modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        ),
        shape = RoundedCornerShape(3.dp),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun LoginButton() {
    Button(
        shape = RoundedCornerShape(3.dp),
        colors= ButtonDefaults.buttonColors(Color.Yellow),
        onClick = { /* Handle login button click here */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(color = Color.Yellow)
    ) {
        Text(
            fontSize = 22.sp,
            text = "Sign in",
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black

        )
    }
}
@Composable
fun SignupButton() {
    Button(
        shape = RoundedCornerShape(3.dp),
        colors= ButtonDefaults.buttonColors(Color.Yellow),
        onClick = { /* Handle sign up button click here */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Text(
            text = "Sign up",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold ,
            color = Color.Black,
        )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MidtermTheme {
        Greeting()
    }
}