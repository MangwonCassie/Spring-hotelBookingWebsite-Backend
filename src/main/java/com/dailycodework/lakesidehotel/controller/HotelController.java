package com.dailycodework.lakesidehotel.controller;

import com.dailycodework.lakesidehotel.model.Hotel;
import com.dailycodework.lakesidehotel.service.IHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final IHotelService hotelService;

    @CrossOrigin(origins = {"https://river-hotel-91f9caaa3277.herokuapp.com","https://spring-hotel-booking-website-front-9dlbj6olo-yeoouls-projects.vercel.app", "http://localhost:5173/", "http://127.0.0.1:5173/", "http://127.0.0.1:5173", "https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app","https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app/", "https://spring-hotel-booking-website-front.vercel.app"})
    @PostMapping("/add")
    public ResponseEntity<Hotel> createHotel(@RequestBody Map<String, Object> hotelData) {
        // Hotel 객체를 저장하는 서비스 호출
        // 필요한 필드 추출
        String name = (String) hotelData.get("name");
        String address = (String) hotelData.get("address");
        String kakaoId = (String) hotelData.get("kakaoId");
        String phone = (String) hotelData.get("phone");

        // Hotel 객체 생성 및 저장
        Hotel hotel = hotelService.addNewHotel(name, address, kakaoId, phone);

        return ResponseEntity.ok(hotel);
    }

}