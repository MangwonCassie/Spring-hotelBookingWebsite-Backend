package com.dailycodework.lakesidehotel.response;

<<<<<<< HEAD

=======
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import java.math.BigDecimal;
import java.util.List;

<<<<<<< HEAD
/**
 * @author Simpson Alfred
 */
@Data
@NoArgsConstructor
public class RoomResponse {
=======
@Data
@NoArgsConstructor
public class RoomResponse {


>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    private Long id;
    private String roomType;
    private BigDecimal roomPrice;
    private boolean isBooked;
    private String photo;
<<<<<<< HEAD
    private List<BookingResponse>bookings;
=======
    private List<BookingResponse> bookings;

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8

    public RoomResponse(Long id, String roomType, BigDecimal roomPrice) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }

    public RoomResponse(Long id, String roomType, BigDecimal roomPrice, boolean isBooked,
                        byte[] photoBytes , List<BookingResponse> bookings) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.isBooked = isBooked;
        this.photo = photoBytes != null ? Base64.encodeBase64String(photoBytes) : null;
<<<<<<< HEAD
       this.bookings = bookings;
    }

=======
        this.bookings = bookings;
    }
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
}
