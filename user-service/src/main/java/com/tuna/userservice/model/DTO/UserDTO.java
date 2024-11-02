package com.tuna.userservice.model.DTO;

import com.tuna.userservice.model.data.User;
import com.tuna.userservice.validation.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private Date birthDate;

    public UserDTO() {}
}