package com.example.trip.model.tour

data class ContentIntro(
    val contentid: Long, // 콘텐츠ID
    val contenttypeid: Long, // 콘텐츠타입ID

    val accomcount: String?, // 수용인원
    val chkbabycarriage: String?, // 유모차대여 정보
    val chkcreditcard: String?, // 신용카드가능 정보
    val chkpet: String?, // 전화번호
    val expagerange: String?, // 체험가능 연령
    val expguide: String?, // 체험안내
    val heritage1: String?, // 세계 문화유산 유무
    val heritage2: String?, // 세계 자연유산 유무
    val heritage3: String?, // 세계 기록유산 유무
    val infocenter: String?, // 문의 및 안내
    val opendate: String?, // 개장일
    val parking: String?, // 주차시설
    val restdate: String?, // 쉬는날
    val useseason: String?, // 이용시기
    val usetime: String?, // 이용시간
    val accomcountculture: String?, // 수용인원
    val chkbabycarriageculture: String?, // 유모차대여 정보
    val chkcreditcardculture: String?, // 신용카드가능 정보
    val chkpetculture: String?, // 애완동물동반가능 정보
    val discountinfo: String?, // 할인정보
    val infocenterculture: String?, // 문의 및 안내
    val parkingculture: String?, // 주차시설
    val parkingfee: String?, // 주차요금
    val restdateculture: String?, // 쉬는날
    val usefee: String?, // 이용요금
    val usetimeculture: String?, // 이용시간
    val scale: String?, // 규모
    val spendtime: String?, // 관람 소요시간
    val agelimit: String?, // 관람 가능연령
    val bookingplace: String?, // 예매처
    val discountinfofestival: String?, // 할인정보
    val eventEndDate: String?, // 행사 종료일
    val eventhomepage: String?, // 행사 홈페이지
    val eventplace: String?, // 행사 장소
    val eventStartDate: String?, // 행사 시작일
    val festivalgrade: String?, // 축제등급
    val placeinfo: String?, // 행사장 위치안내
    val playtime: String?, // 공연시간
    val program: String?, // 행사 프로그램
    val spendtimefestival: String?, // 관람 소요시간
    val sponsor1: String?, // 주최자 정보
    val sponsor1tel: String?, // 주최자 연락처
    val sponsor2: String?, // 주관사 정보
    val sponsor2tel: String?, // 주관사 연락처
    val subevent: String?, // 부대행사 내용
    val usetimefestival: String?, // 이용요금

    val distance: String?, // 코스 총거리 (신규항목)
    val infocentertourcourse: String?, // 문의 및 안내
    val schedule: String?, // 코스 일정
    val taketime: String?, // 코스 총 소요 시간 (신규항목)
    val theme: String?, // 코스 테마

    val accomcountleports: String?, // 수용인원
    val chkbabycarriageleports: String?, // 유모차대여 정보
    val chkcreditcardleports: String?, // 신용카드가능 정보
    val chkpetleports: String?, // 애완동물동반가능 정보
    val expagerangeleports: String?, // 체험 가능연령
    val infocenterleports: String?, // 문의 및 안내
    val openperiod: String?, // 개장기간
    val parkingfeeleports: String?, // 주차요금
    val parkingleports: String?, // 주차시설
    val reservation: String?, // 예약안내
    val restdateleports: String?, // 쉬는날
    val scaleleports: String?, // 규모
    val usefeeleports: String?, // 입장료
    val usetimeleports: String?, // 이용시간
    val accomcountlodging: String?, // 수용 가능인원
    val benikia: String?, // 베니키아 여부
    val chekintime: String?, // 입실 시간
    val checkouttime: String?, // 퇴실 시간
    val chkcooking: String?, // 객실내 취사 여부
    val foodplace: String?, // 식음료장
    val goodstay: String?, // 굿스테이 여부
    val hanok: String?, // 한옥 여부
    val infocenterlodging: String?, // 문의 및 안내
    val parkingloding: String?, // 주차시설
    val pickup: String?, // 픽업 서비스
    val roomcount: String?, // 객실수
    val reservationlodging: String?, // 예약안내
    val reservationurl: String?, // 예약안내 홈페이지
    val roomtype: String?, // 객실유형
    val scalelodging: String?, // 규모
    val subfacility: String?, // 부대시설 (기타)
    val barbecue: String?, // 부대시설 정보
    val beauty: String?, // 부대시설 정보
    val beverage: String?, // 부대시설 정보
    val bicycle: String?, // 부대시설 정보
    val campfire: String?, // 부대시설 정보
    val fitness: String?, // 부대시설 정보
    val karaoke: String?, // 부대시설 정보
    val publicbath: String?, // 부대시설 정보
    val publicpc: String?, // 부대시설 정보
    val sauna: String?, // 부대시설 정보
    val seminar: String?, // 부대시설 정보
    val sports: String?, // 부대시설 정보

    val chkbabycarriageshopping: String?, // 유모차대여 정보
    val chkcreditcardshopping: String?, // 신용카드가능 정보
    val chkpetshopping: String?, // 애완동물동반가능 정보
    val culturecenter: String?, // 문화센터 바로가기
    val fairday: String?, // 장서는 날
    val infocentershopping: String?, // 문의 및 안내
    val opendateshopping: String?, // 개장일
    val opentime: String?, // 영업시간
    val parkingshopping: String?, // 주차시설
    val restdateshopping: String?, // 쉬는날
    val restroom: String?, // 화장실 설명
    val saleitem: String?, // 판매 품목
    val saleitemcost: String?, // 판매 품목별 가격
    val scaleshopping: String?, // 규모
    val shopguide: String?, // 매장안내

    val chkcreditcrdfood: String?, // 신용카드가능 정보
    val discountinfofood: String?, // 할인정보
    val firstmenu: String?, // 대표 메뉴
    val infocenterfood: String?, // 문의 및 안내
    val kidsfacility: String?, // 어린이 놀이방 여부
    val opendatefood: String?, // 개업일
    val opentimefood: String?, // 영업시간
    val packing: String?, // 포장 가능
    val parkingfood: String?, // 주차시설
    val reservationfood: String?, // 예약안내
    val restdatefood: String?, // 쉬는날
    val scalefood: String?, // 규모
    val seat: String?, // 좌석수
    val smoking: String?, // 금연/흡연 여부
    val treatmenu: String?, // 취급 메뉴
    val refundregulation: String?, // 환불규정
    val lcnsno: String? // 인허가번호
)
