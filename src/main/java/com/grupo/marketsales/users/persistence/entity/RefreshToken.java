package com.grupo.marketsales.users.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name="refresh_tokens"
)
@Getter
@Setter

public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String token;
    private LocalDateTime expiredDate;
    private Boolean isRevoked;

    public RefreshToken(){}

    public RefreshToken(Integer id, User user, String token, LocalDateTime expiredDate, Boolean isRevoked) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.expiredDate = expiredDate;
        this.isRevoked = isRevoked;
    }
}
