package com.dailycodework.lakesidehotel.controller;

import com.dailycodework.lakesidehotel.exception.InvalidBookingRequestException;
import com.dailycodework.lakesidehotel.exception.ResourceNotFoundException;
import com.dailycodework.lakesidehotel.model.BookedRoom;
import com.dailycodework.lakesidehotel.model.Room;
import com.dailycodework.lakesidehotel.response.BookingResponse;
import com.dailycodework.lakesidehotel.response.RoomResponse;
import com.dailycodework.lakesidehotel.service.IBookingService;
import com.dailycodework.lakesidehotel.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final IBookingService bookingService;
    private final IRoomService roomService;
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @CrossOrigin(origins = {"https://spring-hotel-booking-website-front-9dlbj6olo-yeoouls-projects.vercel.app", "http://localhost:5173/", "http://127.0.0.1:5173/", "http://127.0.0.1:5173", "https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app","https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app/", "https://spring-hotel-booking-website-front.vercel.app"})
    @GetMapping("/all-bookings")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookingResponse>> getAllBookings(){
        List<BookedRoom> bookings = bookingService.getAllBookings();
        List<BookingResponse> bookingResponses = new ArrayList<>();

        for (BookedRoom booking : bookings) {
            BookingResponse bookingResponse = getBookingResponse(booking);
            bookingResponses.add(bookingResponse);
        }
        return ResponseEntity.ok(bookingResponses);
    }

    @CrossOrigin(origins = {"https://river-hotel-91f9caaa3277.herokuapp.com/","https://spring-hotel-booking-website-front-9dlbj6olo-yeoouls-projects.vercel.app", "http://localhost:5173/", "http://127.0.0.1:5173/", "http://127.0.0.1:5173", "https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app","https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app/", "https://spring-hotel-booking-website-front.vercel.app"})
    @PostMapping("/room/{roomId}/booking")
    public ResponseEntity<?> saveBooking(@PathVariable Long roomId,
                                         @RequestBody BookedRoom bookingRequest){

        logger.info("Booking request received: {}", bookingRequest); // 여기서 로그 찍기

        System.out.println("Received booking request (확인중): " + bookingRequest);
        try{
            String confirmationCode = bookingService.saveBooking(roomId, bookingRequest);
            return ResponseEntity.ok(
                    "Room booked successfully, Your booking confirmation code is :"+confirmationCode);

        }catch (InvalidBookingRequestException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = {"https://river-hotel-91f9caaa3277.herokuapp.com/", "https://spring-hotel-booking-website-front-9dlbj6olo-yeoouls-projects.vercel.app", "http://localhost:5173/", "http://127.0.0.1:5173/", "http://127.0.0.1:5173", "https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app","https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app/", "https://spring-hotel-booking-website-front.vercel.app"})
    @GetMapping("/confirmation/{confirmationCode}")
    public ResponseEntity<?> getBookingByConfirmationCode(@PathVariable String confirmationCode) {
        try {
            BookedRoom booking = bookingService.findByBookingConfirmationCode(confirmationCode);
            BookingResponse bookingResponse = getBookingResponse(booking);
            return ResponseEntity.ok(bookingResponse);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());  // "NOT FOUND" 상태 코드와 예외 메시지를 반환

        }
    }

    @CrossOrigin(origins = {"https://river-hotel-91f9caaa3277.herokuapp.com/", "http://localhost:5173/", "http://127.0.0.1:5173/", "http://127.0.0.1:5173", "https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app","https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app/", "https://spring-hotel-booking-website-front.vercel.app"})
    @GetMapping("/user/{email}/bookings")
    public ResponseEntity<List<BookingResponse>> getBookingsByUserEmail(@PathVariable String email) {
        List<BookedRoom> bookings = bookingService.getBookingsByUserEmail(email);
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (BookedRoom booking : bookings) {
            BookingResponse bookingResponse = getBookingResponse(booking);
            bookingResponses.add(bookingResponse);
        }
        return ResponseEntity.ok(bookingResponses);
    }

    @CrossOrigin(origins = {"https://spring-hotel-booking-website-front-9dlbj6olo-yeoouls-projects.vercel.app", "http://localhost:5173/", "http://127.0.0.1:5173/", "http://127.0.0.1:5173", "https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app","https://spring-hotel-booking-website-front-git-master-yeoouls-projects.vercel.app/", "https://spring-hotel-booking-website-front.vercel.app"})
    @DeleteMapping("/booking/{bookingId}/delete")
    public void cancelBooking(@PathVariable Long bookingId){
        bookingService.cancelBooking(bookingId);
    }

    private BookingResponse getBookingResponse(BookedRoom booking) {
        Room theRoom = roomService.getRoomById(booking.getRoom().getId()).get();
        RoomResponse room = new RoomResponse(
                theRoom.getId(),
                theRoom.getRoomType(),
                theRoom.getRoomPrice());//photo 필요 x
        return new BookingResponse(
                booking.getBookingId(), booking.getCheckInDate(),
                booking.getCheckOutDate(),booking.getGuestFullName(),
                booking.getGuestEmail(), booking.getNumOfAdults(),
                booking.getNumOfChildren(), booking.getTotalNumOfGuest(),
                booking.getBookingConfirmationCode(), room);

        //      booking.getCheckOutDate(),booking.getGuest().getFirstName(),
        //                booking.getGuest().getEmail(), booking.getNumOfAdults(), 이걸로 못 가져와 400에러임.
    }
}
