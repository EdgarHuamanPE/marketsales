package com.grupo.marketsales.users.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name="password_reset_tokens"
)

@Getter
@Setter
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="user_id", nullable = false)
    private User user;

    private String token;
    private LocalDateTime expireDate;
    private boolean isUsed;

    public PasswordResetToken(){}

    public PasswordResetToken(Integer id, User user, String token, LocalDateTime expireDate, boolean isUsed) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.expireDate = expireDate;
        this.isUsed = isUsed;
    }
}
