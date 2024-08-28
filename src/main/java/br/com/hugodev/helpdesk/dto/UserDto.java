package br.com.hugodev.helpdesk.dto;

import java.util.UUID;

public record UserDto(UUID id, String username, String name, String email) {
}
