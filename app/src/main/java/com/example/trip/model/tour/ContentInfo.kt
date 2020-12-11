package com.example.triper.model.tour

data class ContentInfo(
    val contentid: Long, // 콘텐츠ID
    val contenttypeid: Long, // 콘텐츠타입ID
    val fldgubun: Long?, // 일련번호
    val infoname: String?, // 제목
    val infotext: String?, // 내용
    val serialnum: Long?, // 반복 일련번호
    val subcontentid: Long?, // 하위 콘텐츠ID
    val subdetailalt: String?, // 코스이미지 설명
    val subdetailimg: String?, // 코스이미지
    val subdetailoverview: String?, // 코스개요
    val subname: String?, // 코스명
    val subnum: Long?, // 반복 일련번호
    val roomcode: String?, // 객실코드
    val roomtitle: String?, // 객실명칭
    val roomsize1: String?, // 객실크기(평)
    val roomcount: String?, // 객실수
    val roombasecount: String?, // 기준인원
    val roommaxcount: String?, // 최대인원
    val roomoffseasonminfee1: String?, // 비수기주중최소
    val roomoffseasonminfee2: String?, // 비수기주말최소
    val roompeakseasonminfee1: String?, // 성수기주중최소
    val roompeakseasonminfee2: String?, // 성수기주말최소
    val roomintro: String?, // 객실소개
    val roombathfacility: String?, // 목욕시설 여부
    val roombath: String?, // 욕조 여부
    val roomhometheater: String?, // 홈시어터 여부
    val roomaircondition: String?, // 에어컨 여부
    val roomtv: String?, // TV 여부
    val roompc: String?, // PC
    val roomcable: String?, // 케이블설치 여부
    val roominternet: String?, // 인터넷 여부
    val roomrefrigerator: String?, // 냉장고 여부
    val roomtoiletries: String?, // 세면도구 여부
    val roomsofa: String?, // 소파 여부
    val roomcook: String?, // 취사용품 여부
    val roomtable: String?, // 테이블 여부
    val roomhairdryer: String?, // 드라이기 여부
    val roomsize2: String?, // 객실크기(평방미터)
    val roomimg1: String?, // 객실사진1
    val roomimg1alt: String?, // 객실사진1 설명
    val roomimg2: String?, // 객실사진2
    val roomimg2alt: String?, // 객실사진2 설명
    val roomimg3: String?, // 객실사진3
    val roomimg3alt: String?, // 객실사진3 설명
    val roomimg4: String?, // 객실사진4
    val roomimg4alt: String?, // 객실사진4 설명
    val roomimg5: String?, // 객실사진5
    val roomimg5alt: String? // 객실사진5 설명
)
