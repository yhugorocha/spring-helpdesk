package br.com.hugodev.helpdesk.dto;

import java.util.UUID;

public record TicketAttachmentDto(UUID id, String filename, String url) {
}
