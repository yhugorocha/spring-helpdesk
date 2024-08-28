package br.com.hugodev.helpdesk.controller;

import br.com.hugodev.helpdesk.domain.User;
import br.com.hugodev.helpdesk.dto.CreateUserDto;
import br.com.hugodev.helpdesk.dto.UserDto;
import br.com.hugodev.helpdesk.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Operation(description = "This method creates a new user in the system")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto user){
        return ResponseEntity.ok(userService.createUser(user));
    }
}