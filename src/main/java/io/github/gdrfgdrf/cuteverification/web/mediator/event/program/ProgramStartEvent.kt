package io.github.gdrfgdrf.cuteverification.web.mediator.event.program

import io.github.gdrfgdrf.cuteverification.web.mediator.event.BaseEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.bus.EventCompanion

class ProgramStartEvent : BaseEvent() {
    class Pre : BaseEvent.Pre()
    class Post: BaseEvent.Post()

    companion object : EventCompanion<ProgramStartEvent>()
}