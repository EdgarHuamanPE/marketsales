package com.grupo.marketsales.users.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
        name="users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email"})
        }
)

@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String email;

    @Column(name="password")
    private String passwordHash;

    private String firstName;
    private String lastName;
    private String phone;
    private String profileImageUrl;
    private boolean isActive;
    private boolean isVerified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })

    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles= new HashSet<>();

    public User(){}

    public User(Integer id, String username, String email, String passwordHash, String firstName, String lastName, String phone, String profileImageUrl, boolean isActive, boolean isVerified, LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.profileImageUrl = profileImageUrl;
        this.isActive = isActive;
        this.isVerified = isVerified;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



}
