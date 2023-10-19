package com.example.githubviewer.Repository


import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.githubviewer.Data.RepoDetails
import com.example.githubviewer.Data.TokenRequest
import com.example.githubviewer.Data.UserInfo
import com.example.githubviewer.Service.ApiService
import com.example.githubviewer.NetworkException
import retrofit2.Response


class AppRepository(private val apiService: ApiService, private val keyValueStorage: KeyValueStorage) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)


    suspend fun getRepositories(): List<Repository> {
        try {
            return apiService.getRepositories()
        } catch (e: Exception) {
            throw NetworkException(e.message ?: "Network error")
        }
    }

    suspend fun getRepository(repoId: String): RepoDetails {
        try {
            return apiService.getRepositoryDetails(repoId)
        } catch (e: Exception) {
            throw NetworkException(e.message ?: "Network error")
        }
    }


    suspend fun getRepositoryReadme(ownerName: String, repositoryName: String, branchName: String): String {
        try {
            val readme = apiService.getRepositoryReadme(ownerName, repositoryName, branchName)
            if (readme != null) {
                return readme.content ?: ""
            } else {
                throw NetworkException("Empty response from API")
            }
        } catch (e: Exception) {
            throw NetworkException(e.message ?: "Network error")
        }
    }

    suspend fun signIn(token: String): UserInfo {
        try {
            val response = apiService.signIn(TokenRequest(token))
            if (response != null) {
                keyValueStorage.authToken = token
                return response
            } else {
                throw NetworkException("Empty response from API")
            }
        } catch (e: Exception) {
            throw NetworkException(e.message ?: "Network error")
        }
    }


}
