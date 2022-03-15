package com.example.http_network.retrofit

import com.example.http_network.model.PageListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//Retrofit에 사용할 인터페이스
interface NetworkService {
    //GET : 서버와 연동할 때 GET 방식으로 해달라는 의미
    @GET("/v2/everything")
    fun getList (
        //QUERY : 서버에 전달되는 데이터
        @Query("q") q : String?,
        @Query("apiKey") apiKey:String?,
        @Query("page") page:Long,
        @Query("pageSize") pageSize:Int
    ) : Call<PageListModel>
}