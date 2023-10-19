package com.example.githubviewer.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubviewer.Repository.AppRepository
import com.example.githubviewer.Repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoriesListViewModel(private val appRepository: AppRepository) : ViewModel() {
    val state = MutableLiveData<State>()

    init {
        loadRepositories()
    }

    fun loadRepositories() {
        viewModelScope.launch {
            try {
                state.value = State.Loading
                val repositories = withContext(Dispatchers.IO) {
                    appRepository.getRepositories()
                }
                if (repositories.isNotEmpty()) {
                    state.value = State.Loaded(repositories)
                } else {
                    state.value = State.Empty
                }
            } catch (e: Exception) {
                state.value = State.Error(e.message ?: "Error loading repositories")
            }
        }
    }

    sealed class State {
        object Loading : State()
        data class Loaded(val repos: List<Repository>) : State()
        data class Error(val error: String) : State()
        object Empty : State()
    }
}
