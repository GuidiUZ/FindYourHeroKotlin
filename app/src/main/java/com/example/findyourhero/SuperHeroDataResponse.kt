package com.example.findyourhero

import com.google.gson.annotations.SerializedName


//Este seria como el objeto padre
data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superHeroes: List<SuperHeroItemResponse>
)


//Este seria como el objeto hijo
data class SuperHeroItemResponse(
    @SerializedName("id") val superHeroId: String,
    @SerializedName("name") val superHeroName: String,
    @SerializedName("image") val superHeroImage: SuperHeroImageResponse
)


data class SuperHeroImageResponse(@SerializedName("url") val url: String)