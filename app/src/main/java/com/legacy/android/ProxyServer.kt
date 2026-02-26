package com.legacy.android

data class ProxyServer(val host: String, val port: String, val token: String, val startingPort: Int = DEFAULT_STARTING_PORT) {

    companion object {
        const val HOST = "56.125.210.160"
        const val SERVER_PORT = "2000"
        const val TOKEN = "12345678"
        const val DEFAULT_STARTING_PORT = 28199

        fun default() = ProxyServer(HOST, SERVER_PORT, TOKEN, DEFAULT_STARTING_PORT)
    }
}
