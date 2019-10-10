package com.vikram.gdgkotlinsample.repositories

import com.vikram.gdgkotlinsample.models.Joke
import com.vikram.gdgkotlinsample.services.ChuckNorrisApiService
import com.vikram.gdgkotlinsample.services.ServiceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Vikram on 2019-10-10.
 */
object JokeRepository {

    private val service: ChuckNorrisApiService = ServiceProvider.jokeServiceObject

    /**
     * This function uses withContext to make sure that
     * the api call is made on IO context, so even if this function is called from Dispatchers.Main
     * it will call api on Dispatchers.Main and return value to
     * called parent coroutine
     *
     * @return @List<String>
     */
    suspend fun getJokeCategories(): List<String> {
        return withContext(Dispatchers.IO) {
            service.getJokeCategories()
        }
    }

    /**
     * This function uses withContext to make sure that
     * the api call is made on IO context, and fetches a random joke from server
     *
     * @return @Joke
     */
    suspend fun getRandomJoke(): Joke {
        return withContext(Dispatchers.IO) {
            service.getRandomJoke()
        }
    }

    /**
     * will return a single joke for the give category
     *
     * @param category - Category of joke
     * @return @Joke
     */
    suspend fun getRandomJokeForCategory(category: String): Joke {
        return withContext(Dispatchers.IO) {
            service.getRandomJokeOfCategory(category)
        }
    }
}