package com.example.findyourhero

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/10027764153963830/search/{name}")
    suspend fun getSuperHeros(@Path("name") superHeroName : String): Response<SuperHeroDataResponse>

    @GET("/api/10027764153963830/{id}")
    suspend fun getSuperHeroDetail(@Path("id") superHeroId:String) : Response<SuperHeroDetailsResponse>

}