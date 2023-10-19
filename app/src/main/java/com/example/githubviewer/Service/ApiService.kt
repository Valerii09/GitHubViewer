package com.example.githubviewer.Service

import com.example.githubviewer.Data.Readme
import com.example.githubviewer.Data.RepoDetails
import com.example.githubviewer.Data.TokenRequest
import com.example.githubviewer.Data.UserInfo
import com.example.githubviewer.Repository.Repository
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("repositories")
    suspend fun getRepositories(): List<Repository> // Замените Repo на Repository

    @GET("repositories/{repoId}")
    suspend fun getRepositoryDetails(@Path("repoId") repoId: String): RepoDetails

    @GET("repositories/{ownerName}/{repositoryName}/readme")
    suspend fun getRepositoryReadme(
        @Path("ownerName") ownerName: String,
        @Path("repositoryName") repositoryName: String,
        @Query("branchName") branchName: String // Добавьте параметр branchName
    ): Readme

    @POST("signin")
    suspend fun signIn(@Body token: TokenRequest): UserInfo // Замените String на TokenRequest
}