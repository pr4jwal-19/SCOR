package com.pro.scor.forms;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserForm {

    private  String userName;
    private String email;
    private String password;
    private String about;
    private String phoneNo;

}
