package br.com.hugodev.helpdesk.controller;

import br.com.hugodev.helpdesk.dto.LoginRequestDto;
import br.com.hugodev.helpdesk.dto.LoginResponseDto;
import br.com.hugodev.helpdesk.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticationUser(@RequestBody LoginRequestDto auth){
        return ResponseEntity.ok(authenticationService.login(auth));
    }
}
