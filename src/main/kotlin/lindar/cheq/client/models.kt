package lindar.cheq.client

import java.io.Serializable

data class RealtimeInterceptionRequest(
    val method: String,
    val clientIP: String,
    val requestURL: String,
    val headerNames: List<String>,
    val host: String,
    val eventType: EventType,
    val resourceType: String? = null,
    val cheqCookie: String? = null,
    val userAgent: String? = null,
    val xForwardedFor: String? = null,
    val referer: String? = null,
    val accept: String? = null,
    val acceptEncoding: String? = null,
    val acceptLanguage: String? = null,
    val acceptCharset: String? = null,
    val origin: String? = null,
    val xRequestedWith: String? = null,
    val connection: String? = null,
    val pragma: String? = null,
    val cacheControl: String? = null,
    val contentType: String? = null,
    val trueClientIP: String? = null,
    val xRealIP: String? = null,
    val remoteAddr: String? = null,
    val forwarded: String? = null,
    val JA3: String? = null,
    val channel: String? = null,
) : Serializable

data class RealtimeInterceptionResponse (val version: String, val isInvalid: Boolean, val threatTypeCode: Int, val requestId: String, val setCookie: String) : Serializable

data class CheqAccessCredentials(val apiUrl: String, val apiKey: String, val tagHash: String, val tagId: String)

enum class EventType (val value: String) {
    PAGE_LOAD("page_load"),
    ADD_PAYMENT("add_payment"),
    ADD_TO_CART("add_to_cart"),
    ADD_TO_WISHLIST("add_to_wishlist"),
    REGISTRATION("registration"),
    PURCHASE("purchase"),
    SEARCH("search"),
    START_TRAIL("start_trail"),
    SUBSCRIBE("subscribe"),
    FORM_SUBMISSION("form_submission"),
    CUSTOM("custom")
}