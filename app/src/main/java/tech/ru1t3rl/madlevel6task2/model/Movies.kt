package tech.ru1t3rl.madlevel6task2.model

import com.google.gson.annotations.SerializedName

data class Movies(
        @SerializedName("results")
        var results: List<Movie>
)