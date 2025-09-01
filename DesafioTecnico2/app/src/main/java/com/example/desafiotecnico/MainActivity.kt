package com.example.desafiotecnico

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.desafiotecnico.data.PreferenciasUsuario
import com.example.desafiotecnico.ui.telas.*
import com.example.desafiotecnico.ui.theme.DesafioTecnicoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = PreferenciasUsuario(this)

        setContent {
            DesafioTecnicoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val startDestination = if (prefs.estaLogado()) "tela_principal" else "tela_splash"

                    NavHost(navController = navController, startDestination = startDestination) {
                        composable("tela_splash") {
                            TelaSplash(navController, prefs)
                        }
                        composable("tela_login") {
                            TelaLogin(navController, prefs)
                        }
                        composable("tela_cadastro") {
                            TelaCadastro(navController, prefs)
                        }
                        composable("tela_principal") {
                            TelaPrincipal(navController, prefs)
                        }
                    }
                }
            }
        }
    }
}