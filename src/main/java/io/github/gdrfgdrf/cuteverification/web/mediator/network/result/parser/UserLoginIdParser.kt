package io.github.gdrfgdrf.cuteverification.web.mediator.network.result.parser

import io.github.gdrfgdrf.cuteverification.web.mediator.network.result.ApiResult

object UserLoginIdParser : IParser<String> {
    override fun parse(apiResult: ApiResult): String {
        val data = apiResult.data ?: throw IllegalArgumentException("data is null")
        val id = data["id"] ?: throw IllegalArgumentException("id is null")
        return id.toString()
    }
}