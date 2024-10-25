package com.bold.network

import android.net.http.NetworkException
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.utils.EmptyContent.status
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkModule {
    private const val API_KEY = "de5553176da64306b86153651221606"

    val client: HttpClient by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.weatherapi.com"
                    path("v1/")
                    parameters.append("key", API_KEY)
                }
            }

            HttpResponseValidator {
                when (status?.value) {
                    in 200..299 -> null
                    in 400..499 -> {
                        val errorType = when (status) {
                            HttpStatusCode.BadRequest -> ErrorType.BadRequest
                            HttpStatusCode.Unauthorized -> ErrorType.Unauthorized
                            HttpStatusCode.Forbidden -> ErrorType.Forbidden
                            HttpStatusCode.NotFound -> ErrorType.NotFound
                            else -> ErrorType.ClientError
                        }
                        ApiResponse.Error(
                            NetworkException(
                                errorType,
                                status?.description.orEmpty()
                            )
                        )
                    }

                    in 500..599 -> {
                        ApiResponse.Error(
                            NetworkException(
                                ErrorType.ServerError,
                                status?.description.orEmpty()
                            )
                        )
                    }

                    else -> ApiResponse.Error(
                        NetworkException(
                            ErrorType.Unknown,
                            status?.description.orEmpty()
                        )
                    )
                }
            }
        }
    }
}


enum class ErrorType {
    BadRequest,
    Unauthorized,
    Forbidden,
    NotFound,
    ClientError,
    ServerError,
    Unknown
}

class NetworkException(val type: ErrorType, override val message: String) : Exception(message)