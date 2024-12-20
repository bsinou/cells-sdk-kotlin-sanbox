package org.sinou.kotlin.android.sampleapp

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

const val SERVER_URL = "https://localhost:8080"
private const val SKIP_SSL_VERIFICATION = true
private const val USER_AGENT = "org.sinou.kotlin.android.sampleapp/v0.1.1 CellsAPI/v2"
private const val PAT =
    "0XmNa4mhWfLhURwFP6_XJVuEDgPDnoa8EKvJB55fY8g.yddonfgcR4oogCzRqIfimzN7dRNk7si3X3TFRL7hYjo"

private val trustAllCerts = UnsecureTrustManager()

private val unsafeSslContext by lazy {
    SSLContext.getInstance("TLS").apply {
        init(null, arrayOf<TrustManager>(trustAllCerts), java.security.SecureRandom())
    }
}

@OptIn(ExperimentalSerializationApi::class)
val jsonInstance = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true
    coerceInputValues = false
    explicitNulls = false
}

private val myHttpClient = HttpClient(OkHttp) {
    Log.e("myHttpClient", "---- Constructing the client")
    install(ContentNegotiation) {
        json(jsonInstance)
    }

    engine {
        preconfigured = OkHttpClient.Builder()
            .sslSocketFactory(unsafeSslContext.socketFactory, trustAllCerts)
            .hostnameVerifier { _, _ -> true }
            .addInterceptor(DummyPatInterceptor(USER_AGENT) { PAT })
            .build()
    }
}

//val myServiceApi = NodeServiceApi("$SERVER_URL/a", myHttpClient.engine)

class NodeService(
    private val appContext: Context,
) {
//     val client = getHttpClient()

//    fun nodeServiceApi(): NodeServiceApi {
//        return NodeServiceApi(getApiURL(), myHttpClient.engine)
//    }

    suspend fun createDummyFile(): Int {

        try {
            val myReq = Request.Builder().url(SERVER_URL)
            Log.i("CreateDummy", "We have a request: $myReq")

            val response = myHttpClient.get { myReq }
        } catch (e: Exception) {
            Log.e("CreateDummy", "Even a get causes: ${e.message}")
            e.printStackTrace()
        }

//        Log.i("CreateDummy", "Before calling create")
//        try {
//            val response = myServiceApi.create(
//                RestCreateRequest(
//                    listOf(
//                        RestIncomingNode(
//                            locator = RestNodeLocator(path = "common-files/test.txt")
//                        )
//                    )
//                )
//            )
//        } catch (e: Exception) {
//            Log.e("CreateDummy", "Et blöaaaa")
//        }
//        Log.e("CreateDummy", "After calling create")
//
//        val response2 = nodeServiceApi().lookup(
//            RestLookupRequest(
//                locators = RestNodeLocators(
//                    many = listOf(
//                        RestNodeLocator(
//                            path = "common-files/*"
//                        )
//                    )
//                )
//            )
//        )
//        return response2.status
        return 200
    }

    fun getApiURL(): String {
        return "$SERVER_URL/a"
    }

    fun getToken(): String {
        return PAT
    }

    fun getHttpClient(): HttpClient {
        return if (SKIP_SSL_VERIFICATION) {
            HttpClient(OkHttp) {
                install(ContentNegotiation) {
                    json()
                }

                engine {
                    preconfigured = OkHttpClient.Builder()
                        .sslSocketFactory(unsafeSslContext.socketFactory, trustAllCerts)
                        .hostnameVerifier { _, _ -> true }
                        .addInterceptor(DummyPatInterceptor(USER_AGENT) { getToken() })
                        .build()
                }
            }
        } else {
            HttpClient(OkHttp) {
                engine {
                    addInterceptor(
                        interceptor = DummyPatInterceptor(USER_AGENT) {
                            getToken()
                        }
                    )
                }
                install(ContentNegotiation) {
                    json()
                }
            }
        }
    }

//    // WARNING: dev only. Skip TLS verification during development and test against a local Pydio Server
//    // Trust manager that does not validate any certificates
//    private val trustAllCerts = UnsecureTrustManager()
//    // SSL context that uses the trust manager
//    private val unsafeSslContext = SSLContext.getInstance("TLS").apply {
//        init(null, arrayOf(trustAllCerts), java.security.SecureRandom())
//    }
}

@SuppressLint("CustomX509TrustManager")
private class UnsecureTrustManager : X509TrustManager {
    @SuppressLint("TrustAllX509TrustManager")
    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
    }

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
}

