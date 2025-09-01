package com.example.desafiotecnico.ui.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.desafiotecnico.data.PreferenciasUsuario
import com.example.desafiotecnico.model.Usuario
import com.example.desafiotecnico.ui.theme.Color.Primary
import com.example.desafiotecnico.ui.theme.Color.PrimaryVariant
import com.example.desafiotecnico.ui.theme.Color.Background
import com.example.desafiotecnico.ui.theme.Color.Error

@Composable
fun TelaCadastro(navController: NavController, prefs: PreferenciasUsuario) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var nomeError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var senhaError by remember { mutableStateOf<String?>(null) }
    var confirmarSenhaError by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    fun validarCampos(): Boolean {
        nomeError = when {
            nome.isBlank() -> "Nome é obrigatório"
            nome.length < 2 -> "Nome deve ter no mínimo 2 caracteres"
            else -> null
        }

        emailError = when {
            email.isBlank() -> "Email é obrigatório"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Email inválido"
            else -> null
        }

        senhaError = when {
            senha.isBlank() -> "Senha é obrigatória"
            senha.length < 6 -> "Senha deve ter no mínimo 6 caracteres"
            else -> null
        }

        confirmarSenhaError = when {
            confirmarSenha.isBlank() -> "Confirmação de senha é obrigatória"
            confirmarSenha != senha -> "As senhas não coincidem"
            else -> null
        }

        return nomeError == null && emailError == null && senhaError == null && confirmarSenhaError == null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.White, Background)
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Primary, PrimaryVariant)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Card(
                    modifier = Modifier.size(80.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f))
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "MA",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Criar nova conta",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Preencha os dados para começar",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White.copy(alpha = 0.8f)
                    )
                )
            }
        }

        // Form
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Cadastro",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Campo Nome
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = nome,
                        onValueChange = {
                            nome = it
                            nomeError = null
                            showError = false
                        },
                        label = { Text("Nome de usuário") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                        isError = nomeError != null,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (nomeError != null) {
                        Text(
                            text = nomeError!!,
                            color = Error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campo Email
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            emailError = null
                            showError = false
                        },
                        label = { Text("E-mail") },
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        isError = emailError != null,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (emailError != null) {
                        Text(
                            text = emailError!!,
                            color = Error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campo Senha
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = senha,
                        onValueChange = {
                            senha = it
                            senhaError = null
                            showError = false
                        },
                        label = { Text("Senha") },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha"
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        isError = senhaError != null,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (senhaError != null) {
                        Text(
                            text = senhaError!!,
                            color = Error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campo Confirmar Senha
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = confirmarSenha,
                        onValueChange = {
                            confirmarSenha = it
                            confirmarSenhaError = null
                            showError = false
                        },
                        label = { Text("Confirmar senha") },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        trailingIcon = {
                            IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                                Icon(
                                    if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = if (confirmPasswordVisible) "Ocultar senha" else "Mostrar senha"
                                )
                            }
                        },
                        visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        isError = confirmarSenhaError != null,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (confirmarSenhaError != null) {
                        Text(
                            text = confirmarSenhaError!!,
                            color = Error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (showError) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Error.copy(alpha = 0.1f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = errorMessage,
                            color = Error,
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botão Cadastrar
                Button(
                    onClick = {
                        if (validarCampos()) {
                            isLoading = true

                            if (prefs.existeUsuarioComEmail(email)) {
                                errorMessage = "Já existe um usuário cadastrado com este email."
                                showError = true
                                isLoading = false
                            } else {
                                val usuario = Usuario(nome, email, senha)
                                prefs.salvarUsuario(usuario)
                                navController.popBackStack() // volta para login
                                isLoading = false
                            }
                        }
                    },
                    enabled = !isLoading,
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Cadastrar")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text("Já tenho conta")
                }
            }
        }
    }
}