package com.tuna.feed_service.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
}
