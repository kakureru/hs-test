package com.hammersystemstest.menu.di

import com.hammersystemstest.menu.data.network.TheMealDbApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/" // TODO to build config

val networkModule = module {

    single {
        provideRoomsApiRetrofit(retrofit = get())
    }

    single<Retrofit> {
        provideRetrofit(okHttpClient = get())
    }

    single {
        provideHttpClient(httpLoggingInterceptor = get())
    }

    single {
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }
}

private fun provideRoomsApiRetrofit(retrofit: Retrofit) = retrofit.create(TheMealDbApi::class.java)

private fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient
    .Builder()
    .addInterceptor(httpLoggingInterceptor)
    .build()