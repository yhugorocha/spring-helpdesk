package br.com.hugodev.helpdesk.dto;

import java.util.UUID;

public record TicketDto(String subject, String description, UUID createdBy) {
}
