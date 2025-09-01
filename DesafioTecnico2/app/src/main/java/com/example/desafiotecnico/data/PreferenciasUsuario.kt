package com.example.desafiotecnico.data

import android.content.Context
import com.example.desafiotecnico.model.Usuario

class PreferenciasUsuario (context: Context) {

    //instancia do SharedPreferences
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "pref-usuario"
        private const val KEY_NOME = "nome"
        private const val KEY_EMAIL = "email"
        private const val KEY_LOGADO = "logado"
        private const val KEY_SENHA = "senha"
    }

    // salva as informações do usuário e substitui o anterior se tiver
    fun salvarUsuario(usuario: Usuario) {
        prefs.edit().apply {
            putString(KEY_NOME, usuario.nome)
            putString(KEY_EMAIL, usuario.email)
            putString(KEY_SENHA, usuario.senha)
            apply()

        }
    }
    //Retorna o usuário salvo ou null se não ter usuário cadastrado
    fun obterUsuario(): Usuario? {
        val nome = prefs.getString(KEY_NOME, null) ?: return null
        val email = prefs.getString(KEY_EMAIL, null) ?: return null
        val senha = prefs.getString(KEY_SENHA, null) ?: return null
        return Usuario(nome, email, senha)
    }

    //verifica se já existe um usuário cadastrado com o email
    fun existeUsuarioComEmail(email: String): Boolean {
        val emailSalvo = prefs.getString(KEY_EMAIL, null) ?: return false
        return emailSalvo.equals(email, ignoreCase = true)
    }

    //valida se as credencias são as mesmas do usuário salvo
    fun validarCredenciais(email: String, senha: String): Boolean {
        val emailSalvo = prefs.getString(KEY_EMAIL, null)
        val senhaSalva = prefs.getString(KEY_SENHA, null)
        return emailSalvo == email && senhaSalva == senha
    }

    //marca o estado do login, mantém o usuário logado quando reiniciar a app
    fun setLogado(logado: Boolean) {
        prefs.edit().putBoolean(KEY_LOGADO, logado).apply()
    }

    //retorna se o usuário está logado
    fun estaLogado(): Boolean = prefs.getBoolean(KEY_LOGADO, false)

    //desloga o usuário sem apagar os dados do usuário
    fun logout() {
        prefs.edit().putBoolean(KEY_LOGADO, false).apply()
    }

    //limpa todos os dados salvos
    fun limparUsuario() {
        prefs.edit().clear().apply()
    }
}