package br.com.hugodev.helpdesk.repository;

import br.com.hugodev.helpdesk.entity.TicketEntity;
import br.com.hugodev.helpdesk.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {

}
