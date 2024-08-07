package br.com.hugodev.helpdesk.controller;

import br.com.hugodev.helpdesk.domain.Ticket;
import br.com.hugodev.helpdesk.domain.User;
import br.com.hugodev.helpdesk.dto.TicketDto;
import br.com.hugodev.helpdesk.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Operation(description = "This method creates a new user in the system")
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketDto ticket){
        return ResponseEntity.ok(ticketService.createTicket(ticket));
    }
}