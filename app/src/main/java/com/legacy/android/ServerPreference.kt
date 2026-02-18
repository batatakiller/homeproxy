package com.legacy.android


import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ServerPreference private constructor(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "settings")


    val SERVER: Flow<ProxyServer> = context.dataStore.data.map(::retrieveServer)


    private fun retrieveServer(pref: Preferences): ProxyServer {
        if (pref[stringPreferencesKey("host")].isNullOrBlank()) return ProxyServer.default()
        return ProxyServer(
            pref[stringPreferencesKey("host")] ?: "",
            pref[stringPreferencesKey("port")] ?: "",
            pref[stringPreferencesKey("token")] ?: "",
            pref[stringPreferencesKey("starting_port")]?.toIntOrNull() ?: ProxyServer.DEFAULT_STARTING_PORT
        )
    }

    suspend fun getCredentials(): Triple<String?, String?, Int?> {
        val pref = context.dataStore.data.first()
        val user = pref[stringPreferencesKey("proxy_user")]
        val pass = pref[stringPreferencesKey("proxy_pass")]
        val port = pref[stringPreferencesKey("proxy_port")]?.toIntOrNull()
        return Triple(user, pass, port)
    }


    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }

    suspend fun saveProxyServer(device: ProxyServer) {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey("host")] = device.host
            pref[stringPreferencesKey("port")] = device.port
            pref[stringPreferencesKey("token")] = device.token
            pref[stringPreferencesKey("starting_port")] = device.startingPort.toString()
        }
    }

    suspend fun saveCredentials(user: String, pass: String, port: Int) {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey("proxy_user")] = user
            pref[stringPreferencesKey("proxy_pass")] = pass
            pref[stringPreferencesKey("proxy_port")] = port.toString()
        }
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        var INSTANCE: ServerPreference? = null
        fun getInstance(context: Context): ServerPreference {
            return INSTANCE ?: ServerPreference(context).also { INSTANCE = it }
        }
    }
}
