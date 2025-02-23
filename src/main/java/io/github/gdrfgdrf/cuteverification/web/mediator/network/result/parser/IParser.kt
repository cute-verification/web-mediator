package io.github.gdrfgdrf.cuteverification.web.mediator.network.result.parser

import io.github.gdrfgdrf.cuteverification.web.mediator.network.result.ApiResult

interface IParser<T> {
    fun parse(apiResult: ApiResult): T
}