package com.genuinecoder.SpringSecurityDemo.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyUser {

    private Long id;
    private String username;
    private String password;
    private String role;

}
