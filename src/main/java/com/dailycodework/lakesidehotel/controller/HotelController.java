package com.dailycodework.lakesidehotel.controller;

import com.dailycodework.lakesidehotel.service.IHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final IHotelService hotelService;

}
