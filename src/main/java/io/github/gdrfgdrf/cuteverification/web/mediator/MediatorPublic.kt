package io.github.gdrfgdrf.cuteverification.web.mediator

import io.github.gdrfgdrf.cuteverification.web.mediator.cache.TokenCache
import io.github.gdrfgdrf.cuteverification.web.mediator.event.program.ProgramStartEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.composeEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.program.ProgramStopEvent

class MediatorPublic {
    var started = false
        private set

    fun start(url: String, username: String, password: String) {
        if (started) {
            throw IllegalStateException("The program is already started")
        }
        composeEvent(ProgramStartEvent.pre(), ProgramStartEvent.post()) {
            WebMediator.start(url, username, password)
            started = true
        }
    }

    fun stop() {
        if (!started) {
            throw IllegalStateException("The program is not started yet")
        }

        composeEvent(ProgramStopEvent.pre(), ProgramStopEvent.post()) {
            WebMediator.stop()
            TokenCache.username(null)
            TokenCache.password(null)
            TokenCache.set(null)

            started = false
        }
    }
}