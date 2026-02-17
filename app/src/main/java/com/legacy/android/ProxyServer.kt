package com.legacy.android

data class ProxyServer(val host: String, val port: String, val token: String) {

    companion object {
        const val HOST = "YOUR_VPS_IP"
        const val SERVER_PORT = "7000"
        const val TOKEN = "CHANGE_ME"

        fun default() = ProxyServer(HOST, SERVER_PORT, TOKEN)
    }
}
