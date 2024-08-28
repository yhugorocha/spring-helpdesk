package br.com.hugodev.helpdesk.controller;

import br.com.hugodev.helpdesk.dto.CreateTicketInterectionDto;
import br.com.hugodev.helpdesk.dto.TicketDto;
import br.com.hugodev.helpdesk.dto.TicketInteractionDto;
import br.com.hugodev.helpdesk.service.TicketAttachmentService;
import br.com.hugodev.helpdesk.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TicketAttachmentService attachmentService;

    @Operation(description = "This method creates a new support ticket in the system")
    @PostMapping
    public ResponseEntity<Object> createTicket(@RequestParam("ticket") String ticket, @RequestParam(value = "files", required = false) MultipartFile[] files) {
        return ResponseEntity.ok(this.ticketService.createTicket(ticket, files));
    }

    @Operation(description = "This method creates a new ticket interaction in the system")
    @PostMapping("/{ticketId}/interaction")
    public ResponseEntity<TicketInteractionDto> createTicketInteraction(@PathVariable UUID ticketId, @RequestParam("interaction") String interection, @RequestParam(value = "files", required = false) MultipartFile[] files){
        return ResponseEntity.ok(ticketService.createTicketInteraction(ticketId, interection, files));
    }

    @Operation(description = "This method get files in the system")
    @GetMapping("/file/{fileId}")
    public ResponseEntity<Resource> getTicketAttachment(@PathVariable() UUID fileId){
        return attachmentService.getFile(fileId);
    }

    @Operation(description = "This method get all support tickets")
    @GetMapping
    public ResponseEntity<List<TicketDto>> getTickets(){
        return ResponseEntity.ok(ticketService.findAll());
    }

    @Operation(description = "This method get ticket interactions")
    @GetMapping("/interaction/{ticketId}")
    public ResponseEntity<List<TicketInteractionDto>> getTicketsinteractions(@PathVariable() UUID ticketId){
        return ResponseEntity.ok(ticketService.findInteractionByTicket(ticketId));
    }




}