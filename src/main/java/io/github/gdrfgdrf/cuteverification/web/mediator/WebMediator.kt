package io.github.gdrfgdrf.cuteverification.web.mediator

import io.github.gdrfgdrf.cuteverification.web.mediator.cache.TokenCache
import io.github.gdrfgdrf.cuteverification.web.mediator.event.composeEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.program.ProgramStartEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.program.ProgramStopEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.network.RetrofitClient
import java.util.*
import java.util.logging.Logger

object WebMediator {
    var started = false
        private set
    val logger: Logger = Logger.getLogger("CuteVerification-WebMediator")
    private val timerTask = object : TimerTask() {
        override fun run() {
            TokenCache.refresh()
        }
    }

    fun start(url: String, username: String, password: String) {
        if (started) {
            throw IllegalStateException("The program is already started")
        }
        composeEvent(ProgramStartEvent.pre(), ProgramStartEvent.post()) {
            RetrofitClient.init(url)

            TokenCache.username(username)
            TokenCache.password(password)

            Timer().schedule(timerTask, 0, 2700000L)
            started = true
        }
    }

    fun stop() {
        if (!started) {
            throw IllegalStateException("The program is not started yet")
        }

        composeEvent(ProgramStopEvent.pre(), ProgramStopEvent.post()) {
            timerTask.cancel()
            TokenCache.username(null)
            TokenCache.password(null)
            TokenCache.set(null)

            started = false
        }

    }
}