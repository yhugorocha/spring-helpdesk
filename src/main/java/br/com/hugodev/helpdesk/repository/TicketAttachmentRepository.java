package br.com.hugodev.helpdesk.repository;

import br.com.hugodev.helpdesk.entity.TicketAttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketAttachmentRepository extends JpaRepository<TicketAttachmentEntity, UUID> {
}
