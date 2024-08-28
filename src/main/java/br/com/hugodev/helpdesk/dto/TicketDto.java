package br.com.hugodev.helpdesk.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record TicketDto(UUID id, UserDto support, String subject, String description, String status, Date createdAt, UserDto createdBy, List<TicketAttachmentDto> attachments) {
}
