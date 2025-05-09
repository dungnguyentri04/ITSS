package com.example.ITSS.models;

import com.example.ITSS.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

//    private String phoneNumber;

    private Date created_at;

    private Date updated_at;

}
