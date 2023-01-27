package lindar.cheq.client

import lindar.cheq.client.api.RealtimeInterceptionResource
import org.apache.hc.client5.http.config.RequestConfig
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.apache.hc.core5.http.io.SocketConfig
import org.apache.hc.core5.util.Timeout

class CheqClient(accessCredentials: CheqAccessCredentials, defaultTimeout: Int = 200) {

    private val httpClient: CloseableHttpClient = HttpClientBuilder.create()
        .disableCookieManagement()
        .setConnectionManager(
            PoolingHttpClientConnectionManagerBuilder.create()
            .setDefaultSocketConfig(
                SocketConfig.custom()
                .setSoTimeout(Timeout.ofMilliseconds(defaultTimeout.toLong()))
                .build())
            .setMaxConnTotal(100)
            .setMaxConnPerRoute(100)
            .build())
        .setDefaultRequestConfig(
            RequestConfig.custom()
            .setConnectTimeout(Timeout.ofMilliseconds(defaultTimeout.toLong()))
            .setResponseTimeout(Timeout.ofMilliseconds(defaultTimeout.toLong()))
            .setConnectionRequestTimeout(Timeout.ofMilliseconds(defaultTimeout.toLong()))
            .build())
        .build();

    private val realtimeInterceptionResource: RealtimeInterceptionResource = RealtimeInterceptionResource(accessCredentials, httpClient, defaultTimeout)
    fun realtimeInterception() = realtimeInterceptionResource
}
