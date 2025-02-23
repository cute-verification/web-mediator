package io.github.gdrfgdrf.cuteverification.web.mediator.network

import io.github.gdrfgdrf.cuteverification.web.mediator.network.result.ApiResult

fun finish(finish: (ApiResult) -> Unit): HttpObserver {
    return HttpObserver.finish(finish)
}

fun error(error: (Throwable) -> Unit): HttpObserver {
    return HttpObserver.error(error)
}