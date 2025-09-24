package com.grupo.marketsales.users.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name="email_verification_tokens"
)

@Getter
@Setter
public class EmailVerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String token;
    @Column (name= "expire_date")
    private LocalDateTime expiredDate;
    private Boolean isRevoked;

    public EmailVerificationToken(Integer id, User user, String token, LocalDateTime expiredDate, Boolean isRevoked) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.expiredDate = expiredDate;
        this.isRevoked = isRevoked;
    }

    public EmailVerificationToken(){}
}
