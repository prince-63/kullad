package com.kullad.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kullad.annotation.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    @NotBlank(message = "Email must not be blank!")
    @Email(message="Please provide a valid email address!")
    @Column(unique = true, nullable = false)
    private String email;

    @PasswordValidator
    @NotBlank(message="Password must not be blank!")
    @Size(min=5, message="Password must be at least 5 characters!")
    @JsonIgnore
    private String password;

    @NotNull(message = "Role is mandatory!")
    @Enumerated(EnumType.STRING)
    private Role role;
}
