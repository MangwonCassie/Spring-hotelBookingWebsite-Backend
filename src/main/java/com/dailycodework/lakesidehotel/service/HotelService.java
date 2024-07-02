package com.dailycodework.lakesidehotel.service;

import com.dailycodework.lakesidehotel.model.Hotel;
import com.dailycodework.lakesidehotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelService implements IHotelService {

    private final IHotelService iHotelService;

    private final HotelRepository hotelRepository;

    @Override
    public Hotel addNewHotel(String name, String address, String kakaoId, String phone) {
       Hotel hotel = new Hotel();
       hotel.setName(name);
       hotel.setAddress(address);
       hotel.setKakaoId(kakaoId);
       hotel.setPhone(phone);
        return hotelRepository.save(hotel);
    }
}
