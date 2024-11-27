package com.tuna.monolithsocialmediaapp.payload.request;

import com.tuna.monolithsocialmediaapp.model.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private LocalDate birthDate;

    public Users toUser() {
        Users users = new Users();
        users.setFirstName(getFirstName());
        users.setLastName(getLastName());
        users.setUserName(getUserName());
        users.setEmail(getEmail());
        users.setPassword(getPassword());
        users.setBirthDate(getBirthDate());
        users.setRegisteredAt(LocalDateTime.now());

        return users;
    }
}
