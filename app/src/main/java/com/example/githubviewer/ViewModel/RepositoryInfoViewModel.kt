package com.example.githubviewer.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubviewer.Data.RepoDetails
import com.example.githubviewer.Repository.AppRepository
import kotlinx.coroutines.launch

class RepositoryInfoViewModel(private val appRepository: AppRepository) : ViewModel() {
    val state = MutableLiveData<State>()

    fun loadRepositoryDetails(repoId: String) {
        viewModelScope.launch {
            try {
                state.value = State.Loading
                val repoDetails = appRepository.getRepository(repoId)
                state.value = State.Loaded(repoDetails)
            } catch (e: Exception) {
                state.value = State.Error(e.message ?: "Error loading repository details")
            }
        }
    }

    sealed class State {
        object Loading : State()
        data class Loaded(val repoDetails: RepoDetails) : State()
        data class Error(val error: String) : State()
    }
}
