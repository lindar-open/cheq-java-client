package lindar.cheq.client

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class CheqClientTest {

    @Disabled
    @Test
    fun t() {
        val cheqClient = CheqClient(CheqAccessCredentials("https://obs.cheqzone.com/", "", "", ""))
        val create = cheqClient.realtimeInterception().create(
            RealtimeInterceptionRequest(
                "GET", "192.168.1.1", "http://localhost", listOf("User-Agent"), "localhost", EventType.CUSTOM, "", "", "MrQ/1", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
            )
        )
        println(create)
    }
}