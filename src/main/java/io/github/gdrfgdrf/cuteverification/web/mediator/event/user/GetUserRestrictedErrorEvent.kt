package io.github.gdrfgdrf.cuteverification.web.mediator.event.user

import io.github.gdrfgdrf.cuteverification.web.mediator.event.BaseEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.bus.EventCompanion

class GetUserRestrictedErrorEvent(
    val id: String,
    val username: String,
    val code: String,
    val ip: String
) : BaseEvent() {

    companion object : EventCompanion<GetUserRestrictedErrorEvent>()

}