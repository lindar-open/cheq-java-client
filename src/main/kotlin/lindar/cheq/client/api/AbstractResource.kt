package lindar.cheq.client.api

import com.lindar.wellrested.WellRestedRequest
import com.lindar.wellrested.vo.Result
import com.lindar.wellrested.vo.ResultBuilder
import com.lindar.wellrested.vo.WellRestedResponse
import lindar.acolyte.util.UrlAcolyte
import lindar.cheq.client.CheqAccessCredentials
import mu.KotlinLogging
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity
import org.apache.hc.core5.http.HttpEntity
import org.apache.hc.core5.http.message.BasicNameValuePair
import java.nio.charset.Charset

abstract class AbstractResource(val accessCredentials: CheqAccessCredentials, private val defaultTimeout: Int) {

    companion object {
        private val log = KotlinLogging.logger {}
    }

    private fun buildRequestFromResourcePath(resourcePath: String): WellRestedRequest {
        val url = UrlAcolyte.safeConcat(accessCredentials.apiUrl, resourcePath)
        return WellRestedRequest.builder()
            .url(url)
            .timeout(defaultTimeout)
            .build()
    }

    protected fun <S : Any> postAndGetRequest(resourcePath: String, objectToPost: Map<String, String>, clazz: Class<S>): Result<S> {
        val request = buildRequestFromResourcePath(resourcePath)

        val httpEntity: HttpEntity = UrlEncodedFormEntity(objectToPost.map { BasicNameValuePair(it.key, it.value) }, Charset.forName("UTF-8"))
        val response = request.post().httpEntity(httpEntity).submit()
        return if (response.isValid) {
            ResultBuilder.successful(response.fromJson().castTo(clazz))
        } else parseErrorResponse(response)
    }

    private fun <T> parseErrorResponse(response: WellRestedResponse): Result<T> {
        if (response.isSocketTimeout) {
            return ResultBuilder.failed<T>()
                .msg("Socket timeout")
                .code(response.statusCode.toString()).buildAndIgnoreData()
        }

        if (response.isConnectionTimeout) {
            return ResultBuilder.failed<T>()
                .msg("Connection timeout")
                .code(response.statusCode.toString()).buildAndIgnoreData()
        }

        return ResultBuilder.failed<T>()
            .msg(response.serverResponse)
            .code(response.statusCode.toString()).buildAndIgnoreData()
    }

}
