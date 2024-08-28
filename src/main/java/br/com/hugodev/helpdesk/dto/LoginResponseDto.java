package br.com.hugodev.helpdesk.dto;

public record LoginResponseDto(String username, String message, String token, boolean status) {
}
