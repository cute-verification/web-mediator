package io.github.gdrfgdrf.cuteverification.web.mediator.event.user

import io.github.gdrfgdrf.cuteverification.web.mediator.event.BaseEvent

class UserJoinEvent(
    val username: String,
    val code: String,
    val ip: String,
) : BaseEvent() {

}