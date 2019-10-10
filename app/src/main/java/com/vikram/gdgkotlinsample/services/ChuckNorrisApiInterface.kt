package com.vikram.gdgkotlinsample.services

import retrofit2.http.GET

/**
 * Created by Vikram on 2019-10-10.
 */
interface ChuckNorrisApiInterface {
    @GET("jokes/random")
    suspend fun getRandomJoke()
}