package com.example.http_network.model

//서버에서 넘어오는 기사 내용을 저장하는 파일
class ItemModel {
    var id : Long = 0
    var author:String? = null
    var title:String? = null
    var description:String? = null
    var urlToImage:String? = null
    var publishedAt:String? = null
}