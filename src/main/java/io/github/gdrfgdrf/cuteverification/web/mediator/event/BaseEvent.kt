package io.github.gdrfgdrf.cuteverification.web.mediator.event

abstract class BaseEvent {
    abstract class Pre : BaseEvent()
    abstract class Post : BaseEvent()
}