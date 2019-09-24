package com.ysn.basekotlinmvp.api.main

import com.ysn.basekotlinmvp.model.myprofile.MyProfileResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MainEndpoints {

    @GET("profile")
    fun getMyProfile(): Observable<MyProfileResponse>

}