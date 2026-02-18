package com.legacy.android

data class ProxyServer(val host: String, val port: String, val token: String, val startingPort: Int = DEFAULT_STARTING_PORT) {

    companion object {
        const val HOST = "YOUR_VPS_IP"
        const val SERVER_PORT = "7000"
        const val TOKEN = "CHANGE_ME"
        const val DEFAULT_STARTING_PORT = 10000

        fun default() = ProxyServer(HOST, SERVER_PORT, TOKEN, DEFAULT_STARTING_PORT)
    }
}
