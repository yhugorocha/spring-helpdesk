package br.com.hugodev.helpdesk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", nullable = false)
    private UUID id;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="is_active", nullable = false)
    private Boolean isActive;

    @Column(name="created_at", nullable = false)
    private Date createdAt;

    @Column(name="created_by", nullable = true)
    private UUID createdBy;

    @Column(name="update_at", nullable = true)
    private Date updateAt;

    @Column(name="update_by", nullable = true)
    private UUID updateBy;

}
