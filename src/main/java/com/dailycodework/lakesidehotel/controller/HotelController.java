package com.dailycodework.lakesidehotel.controller;

import com.dailycodework.lakesidehotel.model.Hotel;
import com.dailycodework.lakesidehotel.service.IHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final IHotelService hotelService;

    @CrossOrigin(origins = {"https://spring-hotel-booking-website-front-9dlbj6olo-yeoouls-projects.vercel.app", "http://localhost:5173/", "http://127.0.0.1:5173/", "http://127.0.0.1:5173", "https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app","https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app/", "https://spring-hotel-booking-website-front.vercel.app"})
    @PostMapping("/add")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        // Hotel 객체를 저장하는 서비스 호출
        Hotel savedHotel = hotelService.addNewHotel(hotel);
        return ResponseEntity.ok(savedHotel);
    }

}