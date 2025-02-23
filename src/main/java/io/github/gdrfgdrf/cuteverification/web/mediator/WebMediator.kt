package io.github.gdrfgdrf.cuteverification.web.mediator

import io.github.gdrfgdrf.cuteverification.web.mediator.cache.TokenCache
import io.github.gdrfgdrf.cuteverification.web.mediator.network.RetrofitClient
import retrofit2.Retrofit
import java.util.*
import java.util.logging.Logger

object WebMediator {
    val logger: Logger = Logger.getLogger("CuteVerification-WebMediator")
    private val timerTask = object : TimerTask() {
        override fun run() {
            TokenCache.refresh()
        }
    }

    fun start(url: String, username: String, password: String) {
        RetrofitClient.init(url)

        TokenCache.username(username)
        TokenCache.password(password)

        Timer().schedule(timerTask, 0, 2700000L)
    }

    fun stop() {
        timerTask.cancel()
    }
}