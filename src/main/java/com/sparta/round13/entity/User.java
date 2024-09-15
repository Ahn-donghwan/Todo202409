package com.sparta.round13.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Column(unique = true) // unique 값 == 중복 허용 X
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void updateUser(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
