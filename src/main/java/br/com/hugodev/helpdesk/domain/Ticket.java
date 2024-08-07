package br.com.hugodev.helpdesk.domain;

import java.util.Date;
import java.util.UUID;

public record Ticket(UUID id, User support, String subject, String description, String status, Date createdAt, User createdBy, Date updateAt, User updateBy) {

}
