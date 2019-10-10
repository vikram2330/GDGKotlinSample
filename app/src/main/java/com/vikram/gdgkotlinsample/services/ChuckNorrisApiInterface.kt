package com.vikram.gdgkotlinsample.services

import com.vikram.gdgkotlinsample.models.Joke
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Vikram on 2019-10-10.
 */
interface ChuckNorrisApiInterface {
    @GET("jokes/random")
    suspend fun getRandomJoke(): Joke

    @GET("jokes/categories")
    suspend fun getJokeCategories(): List<String>

    @GET("jokes/random")
    suspend fun getRandomJokeOfCategory(@Query("category") category: String): Joke
}