package br.com.hugodev.helpdesk.mapper;

import br.com.hugodev.helpdesk.domain.Ticket;
import br.com.hugodev.helpdesk.domain.User;
import br.com.hugodev.helpdesk.dto.CreateTicketDto;
import br.com.hugodev.helpdesk.dto.TicketDto;
import br.com.hugodev.helpdesk.dto.TicketInteractionDto;
import br.com.hugodev.helpdesk.entity.TicketEntity;
import br.com.hugodev.helpdesk.entity.TicketInteractionEntity;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    Ticket toDomain(CreateTicketDto ticketDto);
    TicketEntity toEntity(Ticket ticketDomain);
    TicketDto toDto(TicketEntity ticketEntity);
    TicketInteractionDto toInteractionDto(TicketInteractionEntity ticketInteraction);

    User map(UUID value);
    UUID map(User value);


}
