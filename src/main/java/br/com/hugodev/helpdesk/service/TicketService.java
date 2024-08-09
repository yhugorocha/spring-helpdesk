package br.com.hugodev.helpdesk.service;

import br.com.hugodev.helpdesk.domain.Ticket;
import br.com.hugodev.helpdesk.domain.TicketInteraction;
import br.com.hugodev.helpdesk.dto.TicketDto;
import br.com.hugodev.helpdesk.dto.TicketInterectionDto;
import br.com.hugodev.helpdesk.entity.TicketAttachmentEntity;
import br.com.hugodev.helpdesk.entity.TicketInteractionEntity;
import br.com.hugodev.helpdesk.enums.TicketStatus;
import br.com.hugodev.helpdesk.exception.BusinessException;
import br.com.hugodev.helpdesk.exception.NotFoundException;
import br.com.hugodev.helpdesk.mapper.TicketMapper;
import br.com.hugodev.helpdesk.repository.TicketAttachmentRepository;
import br.com.hugodev.helpdesk.repository.TicketInteractionRepository;
import br.com.hugodev.helpdesk.repository.TicketRepository;
import br.com.hugodev.helpdesk.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketInteractionRepository ticketInteractionRepository;
    private final TicketAttachmentRepository ticketAttachmentRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;

    private final String path;

    public TicketService(TicketRepository ticketRepository, TicketInteractionRepository ticketInteractionRepository, TicketAttachmentRepository ticketAttachmentRepository, UserRepository userRepository, TicketMapper ticketMapper, @Value("${app.path.files}") String filePath) {
        this.ticketRepository = ticketRepository;
        this.ticketInteractionRepository = ticketInteractionRepository;
        this.ticketAttachmentRepository = ticketAttachmentRepository;
        this.userRepository = userRepository;
        this.ticketMapper = ticketMapper;
        this.path = filePath;
    }

    @Transactional(rollbackFor = BusinessException.class)
    public Ticket createTicket(String ticket, MultipartFile[] files)  {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            var ticketDto = objectMapper.readValue(ticket, TicketDto.class);
            var user = userRepository.findById(ticketDto.createdBy()).orElseThrow(()-> new NotFoundException("This user not found in system"));
            var newTicket = ticketMapper.toEntity(ticketDto);

            var dateNow = new Date();
            newTicket.setCreatedAt(dateNow);
            newTicket.setCreatedBy(user);
            newTicket.setStatus(TicketStatus.OPEN);
            ticketRepository.save(newTicket);

            for (MultipartFile file:files){
                var ticketAttachment = new TicketAttachmentEntity();
                ticketAttachment.setTicket(newTicket);
                ticketAttachment.setCreatedAt(dateNow);
                ticketAttachment.setFilename(file.getOriginalFilename());
                ticketAttachment.setCreatedBy(user);
                ticketAttachmentRepository.save(ticketAttachment);
                var caminho = path + ticketAttachment.getId() + "." + extrairExtensao(file.getOriginalFilename());
                Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
            }
            return ticketMapper.toDomain(newTicket);

        }catch (IOException e){
            throw new BusinessException("Error saving ticket");
        }
    }

    public TicketInteraction createTicketInteraction(UUID id, TicketInterectionDto interectionDto) {
        var user = userRepository.findById(interectionDto.user()).orElseThrow(()-> new NotFoundException("This user not found in system"));
        var ticket = ticketRepository.findById(id).orElseThrow(()-> new NotFoundException("This ticket not found in system"));

        var newInterection = new TicketInteractionEntity();
        newInterection.setTicket(ticket);
        newInterection.setSentByUserId(user);
        newInterection.setMessage(interectionDto.message());
        newInterection.setCreatedAt(new Date());
        newInterection.setCreatedBy(user);

        return ticketMapper.toDomainInteraction(ticketInteractionRepository.save(newInterection));
    }

    private String extrairExtensao(String nameFile) {
        int i = nameFile.lastIndexOf(".");
        return nameFile.substring(i + 1);
    }
}
