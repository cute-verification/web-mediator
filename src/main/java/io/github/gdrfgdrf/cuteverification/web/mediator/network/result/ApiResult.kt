package io.github.gdrfgdrf.cuteverification.web.mediator.network.result

import io.github.gdrfgdrf.cuteverification.web.mediator.cache.TokenCache

data class ApiResult(
    var code: Int? = null,
    var success: Boolean? = null,
    var data: MutableMap<String, Any?>? = null
) {
    fun success(): Boolean {
        return success == true
    }

    fun ifSuccess(refreshToken: Boolean = true, work: () -> Unit) {
        if (success == true) {
            work()
            return
        }
        if (refreshToken) {
            TokenCache.refresh()
        }
    }

}