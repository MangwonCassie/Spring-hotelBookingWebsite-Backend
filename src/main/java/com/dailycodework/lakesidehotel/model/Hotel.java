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
    private Long hotelId;

    @JsonProperty("place_name")
    private String name;

    @JsonProperty("road_address_name")
    private String address;

    @JsonProperty("kakaoId")
    private String kakaoId;

    @JsonProperty("phone")
    private String phone;

    @OneToMany(mappedBy="hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Room> rooms;

}
