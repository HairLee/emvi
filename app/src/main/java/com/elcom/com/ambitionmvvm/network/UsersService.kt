package com.elcom.com.ambitionmvvm.network

import com.elcom.com.ambitionmvvm.model.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by Hailpt on 4/3/2018.
 */
interface UsersService {

    @GET
    fun fetchUsers(@Url url: String): Observable<UserResponse>

    @GET
    fun fetchTeacher(@Url url: String): Observable<UserResponse>
}
