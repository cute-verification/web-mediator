package io.github.gdrfgdrf.cuteverification.web.mediator.event.admin

import io.github.gdrfgdrf.cuteverification.web.mediator.event.BaseEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.bus.EventCompanion
import io.github.gdrfgdrf.cuteverification.web.mediator.network.result.ApiResult

class AdminLoginEvent : BaseEvent() {
    class Pre(
        val username: String
    ) : BaseEvent.Pre()
    class Post(
        val username: String,
        val result: ApiResult?
    ) : BaseEvent.Post()

    companion object : EventCompanion<AdminLoginEvent>()
}