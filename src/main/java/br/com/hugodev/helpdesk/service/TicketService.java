package br.com.hugodev.helpdesk.service;

import br.com.hugodev.helpdesk.dto.CreateTicketDto;
import br.com.hugodev.helpdesk.dto.CreateTicketInterectionDto;
import br.com.hugodev.helpdesk.dto.TicketDto;
import br.com.hugodev.helpdesk.dto.TicketInteractionDto;
import br.com.hugodev.helpdesk.entity.TicketInteractionEntity;
import br.com.hugodev.helpdesk.enums.TicketStatus;
import br.com.hugodev.helpdesk.exception.BusinessException;
import br.com.hugodev.helpdesk.exception.NotFoundException;
import br.com.hugodev.helpdesk.mapper.TicketMapper;
import br.com.hugodev.helpdesk.mapper.UserMapper;
import br.com.hugodev.helpdesk.repository.TicketInteractionRepository;
import br.com.hugodev.helpdesk.repository.TicketRepository;
import br.com.hugodev.helpdesk.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketAttachmentService attachmentService;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;
    private final UserMapper userMapper;
    private final TicketInteractionRepository ticketInteractionRepository;
    private final ObjectMapper objectMapper;

    @Transactional(rollbackFor = BusinessException.class)
    public TicketDto createTicket(String ticket, MultipartFile[] files)  {
        try {

            var createTicketDto = objectMapper.readValue(ticket, CreateTicketDto.class);

            var user = userRepository.findById(createTicketDto.createdBy()).orElseThrow(()-> new NotFoundException("This user not found in system"));
            var ticketDomain = ticketMapper.toDomain(createTicketDto);

            var dateNow = new Date();
            ticketDomain.setCreatedAt(dateNow);
            ticketDomain.setCreatedBy(userMapper.toDomain(user));
            ticketDomain.setStatus(TicketStatus.OPEN);
            var tickeEntity = ticketMapper.toEntity(ticketDomain);
            ticketRepository.save(tickeEntity);

            if(files != null){
                for (MultipartFile file:files){
                    this.attachmentService.createTicketAttachment(file, tickeEntity);
                }
            }

            return ticketMapper.toDto(tickeEntity);

        }catch (InvalidDataAccessApiUsageException | JsonProcessingException e) {
            throw new BusinessException("Error saving ticket");
        }
    }

    @Transactional(rollbackFor = BusinessException.class)
    public TicketInteractionDto createTicketInteraction(UUID id, String interation, MultipartFile[] files) {

        try{
            var interectionDto = objectMapper.readValue(interation, CreateTicketInterectionDto.class);

            var user = userRepository.findById(interectionDto.user()).orElseThrow(()-> new NotFoundException("This user not found in system"));
            var ticket = ticketRepository.findById(id).orElseThrow(()-> new NotFoundException("This ticket not found in system"));

            var newInterection = new TicketInteractionEntity();
            newInterection.setTicket(ticket);
            newInterection.setSentByUserId(user);
            newInterection.setMessage(interectionDto.message());
            newInterection.setCreatedAt(new Date());
            newInterection.setCreatedBy(user);
            ticketInteractionRepository.save(newInterection);

            if(files != null){
                for (MultipartFile file:files){
                    this.attachmentService.createTicketAttachment(file, newInterection);
                }
            }

            return ticketMapper.toInteractionDto(newInterection);

        }catch (IOException e){
            throw new BusinessException("Error saving ticket interation");
        }

    }

    public List<TicketDto> findAll() {
        return ticketRepository.findAll().stream().map(ticketMapper::toDto).toList();
    }

    public List<TicketInteractionDto> findInteractionByTicket(UUID id) {

        return ticketInteractionRepository.findAllByTicketId(id).stream().map(ticketMapper::toInteractionDto).toList();
    }
}
