package com.dailycodework.lakesidehotel.request;

<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Simpson Alfred
 */
@Data
public class LoginRequest {
    @NotBlank
=======
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank
    @Email
>>>>>>> f2a4376f1d3c4315c72d88de4738086adcb61fa8
    private String email;
    @NotBlank
    private String password;
}
