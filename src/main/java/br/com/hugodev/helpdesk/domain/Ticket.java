package br.com.hugodev.helpdesk.domain;

import br.com.hugodev.helpdesk.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket {

    private UUID id;
    private User support;
    private String subject;
    private String description;
    private TicketStatus status;
    private Date createdAt;
    private User createdBy;
    private Date updateAt;
    private User updateBy;

}
