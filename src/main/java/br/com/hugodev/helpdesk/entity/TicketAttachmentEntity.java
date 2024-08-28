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
@Table(name = "ticket_attachments")
public class TicketAttachmentEntity {

    @Id
    @Column(name="id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn
    private TicketEntity ticket;

    @ManyToOne
    @JoinColumn(name="ticket_interaction_id", nullable = true)
    private TicketInteractionEntity ticketInteractionId;

    @Column(name="filename", nullable = false)
    private String filename;

    @Column(name="url", nullable = false)
    private String url;

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
