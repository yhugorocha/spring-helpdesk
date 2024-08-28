package br.com.hugodev.helpdesk.dto;

import java.util.List;
import java.util.UUID;

public record TicketInteractionDto(UUID id, String message, List<TicketAttachmentDto> attachments) {
}
