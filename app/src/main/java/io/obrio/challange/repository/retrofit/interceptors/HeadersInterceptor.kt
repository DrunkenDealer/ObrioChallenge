package io.obrio.challange.repository.retrofit.interceptors

import io.obrio.challange.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeadersInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithApiKey = chain.request().newBuilder()
            .addHeader("x-cg-demo-api-key", BuildConfig.API_KEY)
            .build()
        return chain.proceed(requestWithApiKey)
    }
}