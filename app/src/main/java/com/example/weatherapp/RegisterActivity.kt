package com.example.weatherapp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            Scaffold { innerPadding ->
                RegisterPage(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Preview(showBackground = true)
@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeat_password by rememberSaveable { mutableStateOf("") }

    val activity = LocalContext.current as? Activity
    Column(
        modifier = modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = "Cadastro de Usuário",
            fontSize = 10.sp
        )

        OutlinedTextField(
            value = name,
            label = { Text(text = "Digite seu nome") },
            modifier = modifier.fillMaxWidth(fraction = 0.9f),
            onValueChange = { name = it }
        )

        Spacer(modifier = modifier.size(10.dp))

        OutlinedTextField(
            value = email,
            label = { Text(text = "Digite seu e-mail") },
            modifier = modifier.fillMaxWidth(fraction = 0.9f),
            onValueChange = { email = it }
        )

        Spacer(modifier = modifier.size(10.dp))

        OutlinedTextField(
            value = password,
            label = { Text(text = "Digite sua senha") },
            modifier = modifier.fillMaxWidth(fraction = 0.9f),
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = modifier.size(10.dp))

        OutlinedTextField(
            value = repeat_password,
            label = { Text(text = "Digite sua senha novamente") },
            modifier = modifier.fillMaxWidth(fraction = 0.9f),
            onValueChange = { repeat_password = it },
            visualTransformation = PasswordVisualTransformation()
        )

        Row(modifier = modifier) {
            Button(
                onClick = {
                    Toast.makeText(activity, "Registrado com sucesso!", Toast.LENGTH_LONG).show()
                    activity?.finish()
                }, enabled = password == repeat_password
            ) {
                Text("Registrar")
            }
            Button(
                onClick = { name = ""; email = ""; password = ""; repeat_password = "" },
                enabled = name.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty() || password.isNotEmpty()
            ) {
                Text("Limpar")
            }
        }
    }
}