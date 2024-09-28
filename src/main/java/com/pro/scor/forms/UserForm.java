package com.pro.scor.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "Username must be atleast 3 characters")
    private  String userName;
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Please enter a valid email")
    private String email;
    @NotBlank(message = "")
    @Size(min = 7,max = 20,message = "")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,}$", message = "Password must be at least 7 characters long, Atleast One Uppercase letter, One Special Symbol and One Digit")
    private String password;
    @NotBlank(message = "About is required")
    private String about;
    @Size(min = 10, max = 10, message = "")
    @Pattern(regexp = "^\\d{10}$",message = "Phone number must be 10 digits")
    private String phoneNo;

}
