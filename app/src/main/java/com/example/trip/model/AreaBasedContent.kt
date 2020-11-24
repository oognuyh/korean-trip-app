package com.example.trip.model

data class AreaBasedContent(
    val addr1: String?, // 주소
    val addr2: String?, // 상세주소
    val areacode: Long?, // 지역코드
    val cat1: String?, // 대분류
    val cat2: String?, // 중분류
    val cat3: String?, // 소분류
    val contentid: Long, // 콘텐츠ID
    val contenttypeid: Long, // 콘텐츠타입ID
    val createdtime: Long, // 등록일
    val firstimage: String?, // 대표이미지(원본)
    val firstimage2: String?, // 대표이미지(썸네일)
    val mapx: Double?, // GPS X좌표
    val mapy: Double?, // GPS Y좌표
    val mlevel: Int?, // MAP LEVEL
    val modifiedtime: Long, // 수정일
    val readcount: Long?, // 조회수
    val sigungucode: Long?, // 시군구코드
    val tel: String?, // 전화번호
    val title: String, // 제목
    val booktour: Int?, // 교과서 속 여행지여부
    val zipcode: String? // 우편번호
)