package com.dailycodework.lakesidehotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD

import java.time.LocalDate;

/**
 * @author Simpson Alfred
 */
=======
import java.time.LocalDate;

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
<<<<<<< HEAD
public class BookedRoom {
=======

public class BookedRoom {

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long bookingId;

    @Column(name = "check_in")
    private LocalDate checkInDate;

    @Column(name = "check_out")
    private LocalDate checkOutDate;

    @Column(name = "guest_fullName")
<<<<<<< HEAD
    private String guestFullName; 
=======
    private String guestFullName;
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8

    @Column(name = "guest_email")
    private String guestEmail;

    @Column(name = "adults")
    private int NumOfAdults;

    @Column(name = "children")
    private int NumOfChildren;

    @Column(name = "total_guest")
    private int totalNumOfGuest;

    @Column(name = "confirmation_Code")
    private String bookingConfirmationCode;

<<<<<<< HEAD
    @Column(name = "latitude") // 위도
    private double latitude;

    @Column(name = "longitude") // 경도
    private double longitude;
=======
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public void calculateTotalNumberOfGuest(){
<<<<<<< HEAD
        this.totalNumOfGuest = this.NumOfAdults + NumOfChildren;
    }

    public void setNumOfAdults(int numOfAdults) {
        NumOfAdults = numOfAdults;
=======
        this.totalNumOfGuest = this.NumOfAdults + this.NumOfChildren; //클래스 내부 값 찾아서 매개변수 필요x
    }

    public void setNumOfAdults(int numOfAdults) {
        this.NumOfAdults =  numOfAdults;
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
        calculateTotalNumberOfGuest();
    }

    public void setNumOfChildren(int numOfChildren) {
<<<<<<< HEAD
        NumOfChildren = numOfChildren;
=======
        this.NumOfChildren = numOfChildren;
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
        calculateTotalNumberOfGuest();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
