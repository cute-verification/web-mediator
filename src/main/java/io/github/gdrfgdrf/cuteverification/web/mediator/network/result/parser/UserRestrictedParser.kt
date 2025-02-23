package io.github.gdrfgdrf.cuteverification.web.mediator.network.result.parser

import io.github.gdrfgdrf.cuteverification.web.mediator.network.result.ApiResult

object UserRestrictedParser : IParser<Boolean> {
    override fun parse(apiResult: ApiResult): Boolean {
        val data = apiResult.data ?: throw IllegalArgumentException("data is null")
        val restricted = data["restricted"] ?: throw IllegalArgumentException("restricted is null")
        return java.lang.Boolean.parseBoolean(restricted.toString())
    }
}