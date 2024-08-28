package br.com.hugodev.helpdesk.dto;

import java.util.UUID;

public record CreateTicketInterectionDto(UUID user, String message) {
}
