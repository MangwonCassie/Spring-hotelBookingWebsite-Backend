package com.dailycodework.lakesidehotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("hotel_id")
    private Long hotelId;

    @JsonProperty("address")
    private String address;

    @JsonProperty("kakaoId") // 프론트 key값과 일치
    private String kakaoId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone")
    private String phone;

    @OneToMany(mappedBy="hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Room> rooms;

}
