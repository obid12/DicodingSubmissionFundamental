package com.example.submissionandroidfundamental.utils.network

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newReq = chain.request().newBuilder().addHeader(
            "Authorization",
            "token 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"
        ).build()

        return chain.proceed(newReq)
    }

}