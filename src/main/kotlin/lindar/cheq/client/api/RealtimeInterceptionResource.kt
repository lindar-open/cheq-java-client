package lindar.cheq.client.api

import com.lindar.wellrested.vo.Result
import lindar.cheq.client.CheqAccessCredentials
import lindar.cheq.client.RealtimeInterceptionRequest
import lindar.cheq.client.RealtimeInterceptionResponse


class RealtimeInterceptionResource(accessCredentials: CheqAccessCredentials, defaultTimeout: Int) : AbstractResource(accessCredentials, defaultTimeout) {

    fun create(request: RealtimeInterceptionRequest): Result<RealtimeInterceptionResponse> {
        val requestParams = mutableMapOf<String, String>()

        requestParams["ApiKey"] = accessCredentials.apiKey
        requestParams["TagHash"] = accessCredentials.tagHash
        requestParams["EventType"] = request.eventType.value
        requestParams["Method"] = request.method
        requestParams["ClientIP"] = request.clientIP
        requestParams["RequestURL"] = request.requestURL
        requestParams["HeaderNames"] = request.headerNames.joinToString(separator = ",")
        requestParams["Host"] = request.host

        if (request.resourceType != null) {
            requestParams["ResourceType"] = request.resourceType
        }

        if (request.cheqCookie != null) {
            requestParams["CheqCookie"] = request.cheqCookie
        }

        if (request.userAgent != null) {
            requestParams["UserAgent"] = request.userAgent
        }

        if (request.xForwardedFor != null) {
            requestParams["XForwardedFor"] = request.xForwardedFor
        }

        if (request.referer != null) {
            requestParams["Referer"] = request.referer
        }

        if (request.accept != null) {
            requestParams["Accept"] = request.accept
        }

        if (request.acceptEncoding != null) {
            requestParams["AcceptEncoding"] = request.acceptEncoding
        }

        if (request.acceptLanguage != null) {
            requestParams["AcceptLanguage"] = request.acceptLanguage
        }

        if (request.acceptCharset != null) {
            requestParams["AcceptCharset"] = request.acceptCharset
        }

        if (request.origin != null) {
            requestParams["Origin"] = request.origin
        }

        if (request.xRequestedWith != null) {
            requestParams["XRequestedWith"] = request.xRequestedWith
        }

        if (request.connection != null) {
            requestParams["Connection"] = request.connection
        }

        if (request.pragma != null) {
            requestParams["Pragma"] = request.pragma
        }

        if (request.cacheControl != null) {
            requestParams["CacheControl"] = request.cacheControl
        }

        if (request.contentType != null) {
            requestParams["ContentType"] = request.contentType
        }

        if (request.trueClientIP != null) {
            requestParams["TrueClientIP"] = request.trueClientIP
        }

        if (request.xRealIP != null) {
            requestParams["XRealIP"] = request.xRealIP
        }

        if (request.remoteAddr != null) {
            requestParams["RemoteAddr"] = request.remoteAddr
        }

        if (request.forwarded != null) {
            requestParams["Forwarded"] = request.forwarded
        }

        if (request.JA3 != null) {
            requestParams["JA3"] = request.JA3
        }

        if (request.channel != null) {
            requestParams["Channel"] = request.channel
        }

        return postAndGetRequest("/v1/realtime-interception", requestParams, RealtimeInterceptionResponse::class.java)
    }

}