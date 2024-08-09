package br.com.hugodev.helpdesk.controller;

import br.com.hugodev.helpdesk.domain.TicketInteraction;
import br.com.hugodev.helpdesk.dto.TicketInterectionDto;
import br.com.hugodev.helpdesk.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Operation(description = "This method creates a new support ticket in the system")
    @PostMapping
    public ResponseEntity<Object> createTicket(@RequestParam("ticket") String ticket, @RequestParam("ticket_files") MultipartFile[] files) {
        return ResponseEntity.ok(ticketService.createTicket(ticket, files));
    }

    @Operation(description = "This method creates a new ticket interaction in the system")
    @PostMapping("/{id}/interaction")
    public ResponseEntity<TicketInteraction> createTicketInteraction(@PathVariable UUID id, @RequestBody TicketInterectionDto interectionDto){
        return ResponseEntity.ok(ticketService.createTicketInteraction(id, interectionDto));
    }


}