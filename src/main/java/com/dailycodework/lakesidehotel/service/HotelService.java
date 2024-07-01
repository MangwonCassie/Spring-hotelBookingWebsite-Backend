package com.dailycodework.lakesidehotel.service;

import com.dailycodework.lakesidehotel.model.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelService implements IHotelService {

    private final IHotelService iHotelService;


    @Override
    public Hotel addNewHotel(String name, String address, String kakaoId, String phone) {
        return null;
    }
}
