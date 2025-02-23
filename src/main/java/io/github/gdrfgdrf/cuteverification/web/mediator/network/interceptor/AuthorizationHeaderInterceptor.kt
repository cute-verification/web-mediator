package io.github.gdrfgdrf.cuteverification.web.mediator.network.interceptor

import io.github.gdrfgdrf.cuteverification.web.mediator.cache.TokenCache
import okhttp3.Interceptor
import okhttp3.Response

object AuthorizationHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = TokenCache.current()
        if (token.isNullOrBlank()) {
            return chain.proceed(request)
        }

        val rewriteRequest = request.newBuilder()
            .header("Authorization", token)
            .build()
        return chain.proceed(rewriteRequest)
    }
}