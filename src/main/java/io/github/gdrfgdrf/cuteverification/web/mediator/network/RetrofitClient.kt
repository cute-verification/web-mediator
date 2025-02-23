package io.github.gdrfgdrf.cuteverification.web.mediator.network

import io.github.gdrfgdrf.cuteverification.web.mediator.network.interceptor.AuthorizationHeaderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val OK_HTTP_CLIENT: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(AuthorizationHeaderInterceptor)
        .build()

    private lateinit var retrofit: Retrofit

    fun init(url: String) {
        retrofit = Retrofit.Builder()
            .client(OK_HTTP_CLIENT)
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(url)
            .build()
    }

    fun <T> create(service: Class<T>): T = retrofit.create(service)
}