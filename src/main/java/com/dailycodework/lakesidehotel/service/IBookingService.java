package com.dailycodework.lakesidehotel.service;

import com.dailycodework.lakesidehotel.model.BookedRoom;

import java.util.List;

public interface IBookingService {

    List<BookedRoom> getAllBookingsByRoomId(Long roomId);
}
