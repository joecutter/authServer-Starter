package com.kisokolab.authserver.model.req;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupReqModel implements Serializable {
    @NotNull
    @Email(message = "Email must should be valid")
    String email;
    @NotNull( message = "Username must not be greater than 10 characters")
    String username;
    @NotNull (message = "Phone must should be valid")
    String phone;
    @NotNull
    @Min(value = 6, message = "Password must be greater than 6 characters")
    String password;
    Set<String> roles;
}

