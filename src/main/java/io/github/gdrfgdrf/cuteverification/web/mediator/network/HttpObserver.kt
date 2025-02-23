package io.github.gdrfgdrf.cuteverification.web.mediator.network

import io.github.gdrfgdrf.cuteverification.web.mediator.cache.TokenCache
import io.github.gdrfgdrf.cuteverification.web.mediator.network.result.ApiResult
import io.github.gdrfgdrf.cuteverification.web.mediator.utils.logError
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class HttpObserver private constructor(
    private val finishCallback: ((ApiResult) -> Unit)?,
    private val errorCallback: ((Throwable) -> Unit)?,
    var autoRefreshToken: Boolean = true
): Observer<ApiResult> {
    override fun onSubscribe(d: Disposable) {}

    override fun onNext(t: ApiResult) {
        runCatching {
            if (finishCallback != null) {
                this.finishCallback(t)
            }
        }.onFailure {
            "An error occurred at processing a http result".logError(it)
        }
    }

    override fun onError(e: Throwable) {
        if (errorCallback != null) {
            this.errorCallback(e)
        }
    }

    override fun onComplete() {}

    companion object {
        fun finish(finishCallback: (ApiResult) -> Unit): HttpObserver {
            return HttpObserver(finishCallback = finishCallback, errorCallback = {
                "Network error".logError(it)
            })
        }

        fun error(errorCallback: ((Throwable) -> Unit)?): HttpObserver {
            return HttpObserver(null, errorCallback)
        }
    }

    class Builder {
        private var finishCallback: ((ApiResult) -> Unit)? = null
        private var errorCallback: ((Throwable) -> Unit)? = null

        fun finish(finishCallback: ((ApiResult) -> Unit)?): Builder {
            this.finishCallback = finishCallback
            return this
        }

        fun error(errorCallback: ((Throwable) -> Unit)?): Builder {
            this.errorCallback = errorCallback
            return this
        }

        fun build(): HttpObserver {
            return HttpObserver(finishCallback, errorCallback)
        }
    }
}