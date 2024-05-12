package com.dailycodework.lakesidehotel.response;

<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
=======
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

<<<<<<< HEAD
/**
 * @author Simpson Alfred
 */

=======
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
@Data

@NoArgsConstructor
public class JwtResponse {
<<<<<<< HEAD
=======

>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    private Long id;
    private String email;
    private String token;
    private String type = "Bearer";
    private List<String> roles;

    public JwtResponse(Long id, String email, String token, List<String> roles) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.roles = roles;
    }
}
