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
import org.sinou.android.kotlin.openapi.api.NodeServiceApi
import org.sinou.android.kotlin.openapi.model.RestCreateRequest
import org.sinou.android.kotlin.openapi.model.RestIncomingNode
import org.sinou.android.kotlin.openapi.model.RestNodeLocator
import java.security.cert.X509Certificate
import java.util.UUID
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

//const val SERVER_URL = "https://localhost:8080"
const val SERVER_URL = "https://10.0.2.2:8080"
private const val SKIP_SSL_VERIFICATION = true
private const val USER_AGENT = "org.sinou.kotlin.android.sampleapp/v0.1.1 CellsAPI/v2"
private const val PAT =
    "0XmNa4mhWfLhURwFP6_XJVuEDgPDnoa8EKvJB55fY8g.yddonfgcR4oogCzRqIfimzN7dRNk7si3X3TFRL7hYjo"


//val myServiceApi = NodeServiceApi("$SERVER_URL/a", myHttpClient.engine)

// SSL context that uses the unsecured trust manager
private val unsafeSslContext by lazy {
    SSLContext.getInstance("TLS").apply {
        init(null, arrayOf<TrustManager>(trustAllCerts), java.security.SecureRandom())
    }
}

class NodeService(
    private val appContext: Context,
) {

    private val logTag = "NodeService"
//     private val client = getHttpClient()

    fun nodeServiceApi(): NodeServiceApi {
        // return NodeServiceApi(getApiURL(), getHttpClient().engine)
        return NodeServiceApi(getApiURL(), unsecureHttpClient.engine)
    }

    suspend fun pingServer(): Int {
        try {
            val response = getHttpClient().get { Request.Builder().url(SERVER_URL) }
            return response.status.value
        } catch (e: Exception) {
            Log.e(logTag, "unexpected error while pinging server at $SERVER_URL: ${e.message}")
            return 503
        }
    }

    suspend fun createDummyFile(): Int {
        Log.d(logTag, "Before calling create")
        val id: String = UUID.randomUUID().toString()
        val newPath = "common-files/test-$id.txt"
        try {
            val myServiceApi = nodeServiceApi()
            val response = myServiceApi.create(
                RestCreateRequest(
                    listOf(
                        RestIncomingNode(
                            locator = RestNodeLocator(path = newPath)
                        )
                    )
                )
            )
            return response.status
        } catch (e: Exception) {
            Log.e(logTag, "could not create file at $newPath, cause: ${e.message}")
            return 503
        }
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
    }

    fun getApiURL(): String {
        return "$SERVER_URL/a"
    }

    fun getHttpClient(): HttpClient {
        return if (SKIP_SSL_VERIFICATION) {
            unsecureHttpClient
        } else {
            basicHttpClient
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val jsonInstance = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        coerceInputValues = false
        explicitNulls = false
    }

    private val basicHttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(jsonInstance)
        }
        engine {
            addInterceptor(interceptor = DummyPatInterceptor(USER_AGENT) { PAT })
        }
    }

    // WARNING: dev only. Skip TLS verification during development and test against a local Pydio Server
    private val unsecureHttpClient = HttpClient(OkHttp) {
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

}

// Trust manager that does not validate any certificates
private val trustAllCerts = UnsecureTrustManager()

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
