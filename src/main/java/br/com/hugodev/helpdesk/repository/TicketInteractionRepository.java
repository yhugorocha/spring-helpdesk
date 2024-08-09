package br.com.hugodev.helpdesk.repository;

import br.com.hugodev.helpdesk.entity.TicketInteractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketInteractionRepository extends JpaRepository<TicketInteractionEntity, UUID> {
}
