package com.android.dice.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("User-Agent", "DiceTestApp/1.0.0 ( info@mobeparadigm.co.uk )")
                .build()
        )
    }
}
