package com.dailycodework.lakesidehotel.controller;

import com.dailycodework.lakesidehotel.model.Hotel;
import com.dailycodework.lakesidehotel.service.IHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final IHotelService hotelService;

    @PostMapping("/add")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        // Hotel 객체를 저장하는 서비스 호출
        Hotel savedHotel = hotelService.addNewHotel(hotel);
        return ResponseEntity.ok(savedHotel);
    }

}