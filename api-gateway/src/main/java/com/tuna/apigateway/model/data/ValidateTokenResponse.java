package com.tuna.apigateway.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTokenResponse {
    private boolean isValid;
    private String userName;
}
