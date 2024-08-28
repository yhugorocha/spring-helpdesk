package br.com.hugodev.helpdesk.dto;

public record CreateUserDto(String username, String password, String name, String email) {
}
