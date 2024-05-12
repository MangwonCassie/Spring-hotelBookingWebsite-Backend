package com.dailycodework.lakesidehotel.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

<<<<<<< HEAD
/**
 * @author Simpson Alfred
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

=======
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookingResponse {
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String guestName;

    private String guestEmail;

<<<<<<< HEAD
    private int numOfAdults;

    private int numOfChildren;

    private int totalNumOfGuests;
=======
    private int adults;

    private int children;

    private int total_guest;
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8

    private String bookingConfirmationCode;

    private RoomResponse room;

<<<<<<< HEAD
    public BookingResponse(Long id, LocalDate checkInDate, LocalDate checkOutDate,
                           String bookingConfirmationCode) {
=======

    public BookingResponse(Long id, LocalDate checkInDate, LocalDate checkOutDate, String bookingConfirmationCode) {
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
<<<<<<< HEAD
=======

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
}
