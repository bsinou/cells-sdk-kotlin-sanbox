package org.sinou.kotlin.android.sampleapp

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.*
import okhttp3.OkHttpClient
import org.sinou.android.kotlin.openapi.api.NodeServiceApi
import org.sinou.android.kotlin.openapi.model.RestCreateRequest
import org.sinou.android.kotlin.openapi.model.RestIncomingNode
import org.sinou.android.kotlin.openapi.model.RestLookupRequest
import org.sinou.android.kotlin.openapi.model.RestNodeLocator
import org.sinou.android.kotlin.openapi.model.RestNodeLocators
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

private const val SERVER_URL = "https://localhost:8080/"
private const val SKIP_SSL_VERIFICATION = true
private const val USER_AGENT = "org.sinou.kotlin.android.sampleapp/v0.1.1 CellsAPI/v2"
private const val PAT =
    "lh1-etwWlkVUtejao-XrWvgtS0FyAi_fRo2gEmWn77w.QMrtx4oejSiDj0dI2oskUI2lAT6IlF2epcpT1Ys326A"

class NodeService(
    private val appContext: Context,
) {
    val client = getHttpClient()

    fun nodeServiceApi(): NodeServiceApi {
        return NodeServiceApi(getApiURL(), client.engine)
    }

    suspend fun createDummyFile(): Int {
        val response = nodeServiceApi().create(
            RestCreateRequest(
                listOf(
                    RestIncomingNode(
                        locator = RestNodeLocator(path = "common-files/test.txt")
                    )
                )
            )
        )
        val response2 = nodeServiceApi().lookup(
            RestLookupRequest(
                locators = RestNodeLocators(
                    many = listOf(
                        RestNodeLocator(
                            path = "common-files/*"
                        )
                    )
                )
            )
        )
        return response.status
    }

    fun getApiURL(): String {
        return "$SERVER_URL/a"
    }

    fun getToken(): String {
        return "$PAT"
    }

    fun getHttpClient(): HttpClient {
        return if (SKIP_SSL_VERIFICATION) {
            HttpClient(OkHttp) {
                engine {
                    preconfigured = OkHttpClient.Builder()
                        // .sslSocketFactory(unsafeSslContext.socketFactory, trustAllCerts)
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
            }
        }
    }


    // WARNING: dev only. Skip TLS verification during development and test against a local Pydio Server
    // Trust manager that does not validate any certificates
    val trustAllCerts = object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    }

    // SSL context that uses the trust manager
    val unsafeSslContext = SSLContext.getInstance("SSL").apply {
        init(null, arrayOf(trustAllCerts), java.security.SecureRandom())
    }


}
