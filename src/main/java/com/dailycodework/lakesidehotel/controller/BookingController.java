package com.dailycodework.lakesidehotel.controller;

import com.dailycodework.lakesidehotel.exception.InvalidBookingRequestException;
import com.dailycodework.lakesidehotel.exception.ResourceNotFoundException;
import com.dailycodework.lakesidehotel.model.BookedRoom;
import com.dailycodework.lakesidehotel.response.BookingResponse;
import com.dailycodework.lakesidehotel.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/bookings")
@RestController
public class BookingController {

    private final IBookingService bookingService;

    @GetMapping("all-bookings")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        List<BookedRoom> bookings = bookingService.getAllBookings();
        List<BookingResponse> bookingResponses = new ArrayList<>();

        for (BookedRoom booking : bookings) {
            BookingResponse bookingResponse = getBookingResponse(booking);
            bookingResponses.add(bookingResponse);
        }
        return ResponseEntity.ok(bookingResponses);
    }


    private BookingResponse getBookingResponse(BookedRoom booking) {
        return null;
    }

    @GetMapping("/confirmation/{confirmationCode}")
    public ResponseEntity<?> getBookingByConfirmationCode(@PathVariable String confirmationCode) {
        try {
            BookedRoom booking = bookingService.findByBookingByConfirmationCode(confirmationCode);
            BookingResponse bookingResponse = getBookingResponse(booking);
            return ResponseEntity.ok(bookingResponse);
        } catch (ResourceNotFoundException ex) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            // "NOT FOUND" 상태 코드와 예외 메시지를 반환
        }
    }

    @PostMapping("/room/{roomId}/booking")
    public ResponseEntity<?> saveBooking(@PathVariable Long roomId, @RequestBody BookedRoom bookingRequest){
        try {
            String confirmationCode = bookingService.saveBooking(roomId, bookingRequest);
            return ResponseEntity.ok("Room booked successfully, Your booking confirmation code is :"+confirmationCode);
        }catch(InvalidBookingRequestException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
