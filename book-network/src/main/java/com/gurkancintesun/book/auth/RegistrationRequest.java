package com.gurkancintesun.book.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
        @NotBlank(message = "Firstname is mandatory")
        String firstName,
        @NotBlank(message = "Lastname is mandatory")
        String lastName,
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email is not formatted.")
        String email,
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, message = "Password should be min. 8 digits")
        String password
) {
}
