package com.example.marvelproject.data.network

import com.example.marvelproject.BuildConfig
import com.example.marvelproject.base.toMD5
import com.example.marvelproject.data.model.ResponseAllCharactersDataModel
import com.example.marvelproject.data.model.ResponseCharacterDataModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MarvelNetwork {
    lateinit var service: MarvelService

    suspend fun getAllCharacters(limit: Int): ResponseAllCharactersDataModel{
        loadRetrofit()
        return service.getAllCharacters(limit)
    }

    suspend fun getCharacter(characterid: Int): ResponseCharacterDataModel {
        loadRetrofit()
        return service.getCharacter(characterid)
    }

    private fun loadRetrofit(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient())
            .build()


        service = retrofit.create(MarvelService::class.java)
    }

   private fun createHttpClient(): OkHttpClient{
        //Create OkhttpClient
        val builder = OkHttpClient.Builder()
            .connectTimeout(90L, TimeUnit.SECONDS)
            .readTimeout(90L, TimeUnit.SECONDS)
            .writeTimeout(90L, TimeUnit.SECONDS)

        //Logger Interceptor
        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)

        val hash = ((System.currentTimeMillis()/1000).toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY).toMD5()

        //App token
        builder.addInterceptor{ chain ->
            var request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
                .addQueryParameter("hash", hash)
                .addQueryParameter("ts", (System.currentTimeMillis()/1000).toString())
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
       return builder.build()
    }


}