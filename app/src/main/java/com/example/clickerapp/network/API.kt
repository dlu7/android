package com.example.clickerapp.network
import android.content.Context
import com.example.clickerapp.model.GifResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("v1/gifs/random")
    fun getRandomGif(@Query("tag") tag: String, @Query("rating") rating: String, @Query("api_key") apiKey: String): Call<GifResponse>


    companion object
    {
        fun create(context: Context) : Api
        {
            val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.giphy.com/")
            .build()

            return retrofit.create(Api::class.java)
        }
    }
}
