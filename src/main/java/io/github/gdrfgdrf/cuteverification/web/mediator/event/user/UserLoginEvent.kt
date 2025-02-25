package io.github.gdrfgdrf.cuteverification.web.mediator.event.user

import io.github.gdrfgdrf.cuteverification.web.mediator.event.BaseEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.bus.EventCompanion
import io.github.gdrfgdrf.cuteverification.web.mediator.user.IUser

abstract class UserLoginEvent(
    val user: IUser
) : BaseEvent() {

    class Failed(user: IUser) : UserLoginEvent(user) {
        companion object : EventCompanion<Failed>()
    }

    class Success(user: IUser) : UserLoginEvent(user) {
        companion object : EventCompanion<Success>()
    }

}