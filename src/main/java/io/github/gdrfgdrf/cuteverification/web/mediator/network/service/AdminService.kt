package io.github.gdrfgdrf.cuteverification.web.mediator.network.service

import io.github.gdrfgdrf.cuteverification.web.mediator.network.result.ApiResult
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface AdminService {
    @POST("/api/v1/admin/login")
    fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Observable<ApiResult>

    companion object : ServiceCompanion<AdminService>()
}