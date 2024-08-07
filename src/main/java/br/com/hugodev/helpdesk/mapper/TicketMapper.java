package br.com.hugodev.helpdesk.mapper;

import br.com.hugodev.helpdesk.domain.Ticket;
import br.com.hugodev.helpdesk.domain.User;
import br.com.hugodev.helpdesk.dto.TicketDto;
import br.com.hugodev.helpdesk.entity.TicketEntity;
import br.com.hugodev.helpdesk.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    Ticket toDomain(TicketEntity ticket);

    TicketEntity toEntity(TicketDto ticket);

    User mapUser(UUID value);

    UserEntity mapUserEntity(UUID value);
}
