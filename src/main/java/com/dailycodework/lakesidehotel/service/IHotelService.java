package com.dailycodework.lakesidehotel.service;

import com.dailycodework.lakesidehotel.model.Hotel;

public interface IHotelService {

    Hotel addNewHotel(String name, String address, String kakaoId, String phone);

}
