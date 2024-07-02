package com.dailycodework.lakesidehotel.repository;

import com.dailycodework.lakesidehotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
