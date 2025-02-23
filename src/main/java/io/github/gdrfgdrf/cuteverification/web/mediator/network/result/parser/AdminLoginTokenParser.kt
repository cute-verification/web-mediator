package io.github.gdrfgdrf.cuteverification.web.mediator.network.result.parser

import io.github.gdrfgdrf.cuteverification.web.mediator.network.result.ApiResult

object AdminLoginTokenParser : IParser<String> {
    override fun parse(apiResult: ApiResult): String {
        val data = apiResult.data ?: throw IllegalArgumentException("data is null")
        val token = data["token"] ?: throw IllegalArgumentException("token is null")
        return token.toString()
    }
}