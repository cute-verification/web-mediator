package io.github.gdrfgdrf.cuteverification.web.mediator.event

import io.github.gdrfgdrf.cuteverification.web.mediator.event.bus.EventCompanion

fun composeEvent(pre: EventCompanion.BuiltEvent, post: EventCompanion.BuiltEvent, work: () -> Unit) {
    pre.post()
    work()
    post.post()
}