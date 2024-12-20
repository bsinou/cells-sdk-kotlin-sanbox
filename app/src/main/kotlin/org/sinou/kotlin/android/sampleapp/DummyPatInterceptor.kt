package org.sinou.kotlin.android.sampleapp

import okhttp3.Interceptor
import okhttp3.Response

class DummyPatInterceptor(
    private val userAgent: String,
    private val getToken: () -> String,
) : Interceptor {

    //    private val logTag = "CellsOAuthInterceptor"
    private val AUTH_HEADER = "Authorization"
    private val USER_AGENT_HEADER = "User-Agent"
    private val DEFAULT_TOKEN_TYPE = "Bearer"

    override fun intercept(chain: Interceptor.Chain): Response {
        var builder = chain.request().newBuilder().addHeader(USER_AGENT_HEADER, userAgent)
            .addHeader(AUTH_HEADER, "$DEFAULT_TOKEN_TYPE ${getToken()}")
        return chain.proceed(builder.build())
    }
}
