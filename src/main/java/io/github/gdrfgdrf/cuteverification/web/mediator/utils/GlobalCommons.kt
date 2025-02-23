package io.github.gdrfgdrf.cuteverification.web.mediator.utils

import io.github.gdrfgdrf.cuteverification.web.mediator.WebMediator

fun String.log() {
    WebMediator.logger.info(this)
}

fun String.logDebug() {
    WebMediator.logger.fine(this)
}

fun String.logError(t: Throwable? = null) {
    WebMediator.logger.severe(this)
    t?.printStackTrace()
}