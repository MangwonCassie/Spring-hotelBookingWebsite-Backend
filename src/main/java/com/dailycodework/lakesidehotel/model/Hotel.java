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

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("kakaoId") // DB의 컬럼명과 매칭
    private String kakaoId;

    @JsonProperty("phone")
    private String phone;

    @OneToMany(mappedBy="hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Room> rooms;

}
