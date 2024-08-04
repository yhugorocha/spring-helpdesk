package br.com.hugodev.helpdesk.controller;

import br.com.hugodev.helpdesk.domain.User;
import br.com.hugodev.helpdesk.dto.UserDto;
import br.com.hugodev.helpdesk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto user){
        return ResponseEntity.ok(userService.createUser(user));
    }
}
