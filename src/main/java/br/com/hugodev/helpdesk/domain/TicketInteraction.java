package br.com.hugodev.helpdesk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketInteraction {

    private UUID id;
    private Ticket ticket;
    private User sentByUserId;
    private String message;
    private Date createdAt;
    private User createdBy;
    private Date updateAt;
    private User updateBy;

}
