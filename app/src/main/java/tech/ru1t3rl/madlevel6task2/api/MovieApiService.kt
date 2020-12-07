package tech.ru1t3rl.madlevel6task2.api

import tech.ru1t3rl.madlevel6task2.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query


// The Api key is stored in apikey.properties in the app project folder.
const val API_KEY: String = tech.ru1t3rl.madlevel6task2.BuildConfig.API_KEY

interface MovieApiService {

    @GET("discover/movie?api_key=${API_KEY}&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    suspend fun getMovies(@Query("primary_release_year") year: String): Movies

}