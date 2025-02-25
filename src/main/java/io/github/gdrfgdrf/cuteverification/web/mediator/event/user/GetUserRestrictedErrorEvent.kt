package io.github.gdrfgdrf.cuteverification.web.mediator.event.user

import io.github.gdrfgdrf.cuteverification.web.mediator.event.BaseEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.bus.EventCompanion
import io.github.gdrfgdrf.cuteverification.web.mediator.user.IUser

class GetUserRestrictedErrorEvent(
    val user: IUser
) : BaseEvent() {

    companion object : EventCompanion<GetUserRestrictedErrorEvent>()

}