package io.github.gdrfgdrf.cuteverification.web.mediator.event.user

import io.github.gdrfgdrf.cuteverification.web.mediator.event.BaseEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.bus.EventCompanion

abstract class UserLoginEvent(
    val username: String,
    val code: String,
    val ip: String
) : BaseEvent() {

    class Failed(username: String, code: String, ip: String) : UserLoginEvent(username, code, ip) {
        companion object : EventCompanion<Failed>()
    }

    class Success(val id: String, username: String, code: String, ip: String) : UserLoginEvent(username, code, ip) {
        companion object : EventCompanion<Success>()
    }

}