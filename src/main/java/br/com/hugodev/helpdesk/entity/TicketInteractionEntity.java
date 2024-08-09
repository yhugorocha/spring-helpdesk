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
@Table(name = "ticket_interactions")
public class TicketInteractionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="ticket_id", nullable = false)
    private TicketEntity ticket;

    @ManyToOne
    @JoinColumn(name="sent_by_user_id", nullable = false)
    private UserEntity sentByUserId;

    @Column(name="message", nullable = false)
    private String message;

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
