package com.vikram.gdgkotlinsample.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Vikram on 2019-10-10.
 */
object ServiceProvider {

    private val baseUrl = "https://api.chucknorris.io/"

    /**
     * with the use of 'by lazy' we can create instance of retrofit lazily
     */
     private val retrofitInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val jokeServiceObject:ChuckNorrisApiService by lazy {
        retrofitInstance.create(ChuckNorrisApiService::class.java)
    }


}