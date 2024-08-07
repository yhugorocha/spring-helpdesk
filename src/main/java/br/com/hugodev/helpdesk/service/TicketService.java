package br.com.hugodev.helpdesk.service;

import br.com.hugodev.helpdesk.domain.Ticket;
import br.com.hugodev.helpdesk.dto.TicketDto;
import br.com.hugodev.helpdesk.entity.TicketEntity;
import br.com.hugodev.helpdesk.enums.TicketStatus;
import br.com.hugodev.helpdesk.exception.NotFoundException;
import br.com.hugodev.helpdesk.mapper.TicketMapper;
import br.com.hugodev.helpdesk.repository.TicketRepository;
import br.com.hugodev.helpdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;


@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final TicketMapper ticketMapper;

    public Ticket createTicket(TicketDto ticket){

        var user = userRepository.findById(ticket.createdBy()).orElseThrow(()-> new NotFoundException("This user not found in system"));
        TicketEntity newTicket = ticketMapper.toEntity(ticket);
        newTicket.setCreatedAt(new Date());
        newTicket.setCreatedBy(user);
        newTicket.setStatus(TicketStatus.OPEN);

        return ticketMapper.toDomain(ticketRepository.save(newTicket));
    }

}
