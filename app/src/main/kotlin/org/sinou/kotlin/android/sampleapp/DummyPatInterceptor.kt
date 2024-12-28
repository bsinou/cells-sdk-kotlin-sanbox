package org.sinou.kotlin.android.sampleapp

import okhttp3.Interceptor
import okhttp3.Response

private const val AUTH_HEADER = "Authorization"
private const val USER_AGENT_HEADER = "User-Agent"
private const val DEFAULT_TOKEN_TYPE = "Bearer"

class DummyPatInterceptor(
    private val userAgent: String,
    private val getToken: () -> String,
) : Interceptor {

    private val logTag = "DummyIntercept"

    override fun intercept(chain: Interceptor.Chain): Response {
        // Log.d(logTag, "about to build the interceptor")
        var builder = chain.request().newBuilder().addHeader(USER_AGENT_HEADER, userAgent)
            .addHeader(AUTH_HEADER, "$DEFAULT_TOKEN_TYPE ${getToken()}")
            .addHeader("Content-Type", "application/json")
            .url(SERVER_URL)
        return chain.proceed(builder.build())
    }
}
