package br.com.hugodev.helpdesk.dto;

import java.util.UUID;

public record CreateTicketDto(String subject, String description, UUID createdBy) {
}
