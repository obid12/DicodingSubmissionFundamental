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