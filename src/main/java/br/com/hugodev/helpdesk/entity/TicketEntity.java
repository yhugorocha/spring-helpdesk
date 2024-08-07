package br.com.hugodev.helpdesk.entity;

import br.com.hugodev.helpdesk.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="support_user_id", nullable = true)
    private UserEntity support;

    @Column(name="subject", nullable = false)
    private String subject;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(name="created_at", nullable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="created_by", nullable = false)
    private UserEntity createdBy;

    @Column(name="update_at", nullable = true)
    private Date updateAt;

    @Column(name="update_by", nullable = true)
    private UUID updateBy;

}
