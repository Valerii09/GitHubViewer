package com.example.githubviewer.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubviewer.Data.TokenRequest
import com.example.githubviewer.Repository.AppRepository
import com.example.githubviewer.Repository.KeyValueStorage
import kotlinx.coroutines.launch

class AuthViewModel(private val appRepository: AppRepository,  private val keyValueStorage: KeyValueStorage) : ViewModel() {
    val token = MutableLiveData<String>()// Объявление переменной token
    val state = MutableLiveData<State>()
    val actions = MutableLiveData<Action>()

    fun onSignButtonPressed(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            state.value = State.InvalidInput("Имя пользователя и пароль не могут быть пустыми")
            return
        }

        state.value = State.Loading

        viewModelScope.launch {
            try {
                // Создайте объект TokenRequest с токеном
                val token = "ваш_токен" // Замените "ваш_токен" на фактический токен
                val tokenRequest = TokenRequest(token)

                // Вызовите метод signIn с объектом TokenRequest
                val response = appRepository.signIn(tokenRequest.toString())

                if (response != null) {
                    // Обработайте успешный ответ
                    keyValueStorage.authToken = token
                    actions.value = Action.RouteToMain
                } else {
                    actions.value = Action.ShowError("Получен недопустимый токен")
                }
            } catch (e: Exception) {
                actions.value = Action.ShowError("Ошибка аутентификации: ${e.message}")
            } finally {
                state.value = State.Idle
            }
        }
    }




    sealed class State {
        object Idle : State()
        object Loading : State()
        data class InvalidInput(val reason: String) : State()
    }

    sealed class Action {
        data class ShowError(val message: String) : Action()
        object RouteToMain : Action()
    }
}
