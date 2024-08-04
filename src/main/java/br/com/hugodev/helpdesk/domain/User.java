package br.com.hugodev.helpdesk.domain;

import java.util.UUID;

public record User(UUID id, String username, String password, String name, String email) {
}
