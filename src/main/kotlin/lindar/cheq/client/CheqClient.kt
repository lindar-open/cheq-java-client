package lindar.cheq.client

import lindar.cheq.client.api.RealtimeInterceptionResource

class CheqClient(accessCredentials: CheqAccessCredentials, defaultTimeout: Int = 2000) {
    private val realtimeInterceptionResource: RealtimeInterceptionResource = RealtimeInterceptionResource(accessCredentials, defaultTimeout)
    fun realtimeInterception() = realtimeInterceptionResource
}