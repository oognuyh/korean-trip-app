package com.example.trip.model.tour

data class Content(
    val contentid: Long, // 콘텐츠ID
    val contenttypeid: Long?, // 콘텐츠타입ID
    val booktour: Int?, // 교과서 속 여행지여부
    val createdtime: Long, // 등록일
    val homepage: String?, // 홈페이지 주소
    val modifiedtime: Long, // 수정일
    val tel: String?, // 전화번호
    val telname: String?, // 전화번호명
    val firstimage: String?, // 대표이미지(원본)
    val firstimage2: String?, // 대표이미지(썸네일)
    val areacode: Long?, // 지역코드
    val sigungucode: Long?, // 시군구코드
    val cat1: String?, // 대분류
    val cat2: String?, // 중분류
    val cat3: String?, // 소분류
    val addr1: String?, // 주소
    val addr2: String?, // 상세주소
    val zipcode: String?, // 우편번호
    val mapx: Double?, // GPS X좌표
    val mapy: Double?, // GPS Y좌표
    val mlevel: Int?, // MAP LEVEL
    val overview: String?, // 콘텐츠 개요 조회
    val title: String, // 제목

    val dist: Long?, // 중심 좌표로부터 거리(단위 m)

    val eventstartdate: String?, // 행사 시작일(축제 정보 요청 시)
    val eventenddate: String // 행사 종료일(축제 정보 요청 시)
)