package io.github.gdrfgdrf.cuteverification.web.mediator.event.bus

import io.github.gdrfgdrf.cuteverification.web.mediator.event.BaseEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.utils.ClassRelationResolver
import io.github.gdrfgdrf.cuteverification.web.mediator.utils.ConstructorMacher
import io.github.gdrfgdrf.cuteverification.web.mediator.utils.GenericTypeResolver

open class EventCompanion<T : BaseEvent> : GenericTypeResolver<T>() {
    fun main(vararg any: Any?): BuiltEvent {
        val event = ConstructorMacher.cache(type, *any).newInstance(*any)
        return BuiltEvent.of(event as BaseEvent)
    }

    fun pre(vararg any: Any?): BuiltEvent {
        val preClass = ClassRelationResolver.findChild(type, "Pre")
        val preEvent = ConstructorMacher.cache(preClass, *any).newInstance(*any)
        return BuiltEvent.of(preEvent as BaseEvent)
    }

    fun post(vararg any: Any?): BuiltEvent {
        val postClass = ClassRelationResolver.findChild(type, "Post")
        val postEvent = ConstructorMacher.cache(postClass, *any).newInstance(*any)
        return BuiltEvent.of(postEvent as BaseEvent)
    }

    class BuiltEvent private constructor(
        val any: BaseEvent
    ) {
        fun post() {
            EventCenter.post(any)
        }

        companion object {
            fun of(any: BaseEvent) = BuiltEvent(any)
        }
    }
}