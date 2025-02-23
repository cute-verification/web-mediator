package io.github.gdrfgdrf.cuteverification.web.mediator.event.bus

import com.google.common.eventbus.EventBus
import io.github.gdrfgdrf.cuteverification.web.mediator.event.BaseEvent

object EventCenter {
    private val eventBus = EventBus()

    fun post(event: BaseEvent) {
        eventBus.post(event)
    }

    fun register(any: Any) {
        eventBus.register(any)
    }

}