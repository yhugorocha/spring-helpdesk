package br.com.hugodev.helpdesk.service;

import br.com.hugodev.helpdesk.entity.TicketAttachmentEntity;
import br.com.hugodev.helpdesk.entity.TicketEntity;
import br.com.hugodev.helpdesk.entity.TicketInteractionEntity;
import br.com.hugodev.helpdesk.exception.BusinessException;
import br.com.hugodev.helpdesk.exception.FileStorageException;
import br.com.hugodev.helpdesk.exception.NotFoundException;
import br.com.hugodev.helpdesk.repository.TicketAttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TicketAttachmentService {

    private final TicketAttachmentRepository attachmentRepository;

    @Value("${app.path.files}")
    private String path;

    public void createTicketAttachment(MultipartFile file, TicketEntity ticket){

        var ticketAttachment = new TicketAttachmentEntity();
        ticketAttachment.setId(UUID.randomUUID());
        ticketAttachment.setTicket(ticket);
        ticketAttachment.setCreatedAt(ticket.getCreatedAt());
        ticketAttachment.setFilename(file.getOriginalFilename());
        ticketAttachment.setCreatedBy(ticket.getCreatedBy());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/tickets/file/")
                .path(ticketAttachment.getId().toString())
                .toUriString();

        ticketAttachment.setUrl(fileDownloadUri);
        attachmentRepository.save(ticketAttachment);
        ticketAttachmentSaveStorage(file, ticketAttachment);
    }

    public void createTicketAttachment(MultipartFile file, TicketInteractionEntity ticket ){

        var ticketAttachment = new TicketAttachmentEntity();
        ticketAttachment.setId(UUID.randomUUID());
        ticketAttachment.setTicketInteractionId(ticket);
        ticketAttachment.setCreatedAt(ticket.getCreatedAt());
        ticketAttachment.setFilename(file.getOriginalFilename());
        ticketAttachment.setCreatedBy(ticket.getCreatedBy());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/tickets/file/")
                .path(ticketAttachment.getId().toString())
                .toUriString();

        ticketAttachment.setUrl(fileDownloadUri);
        attachmentRepository.save(ticketAttachment);
        ticketAttachmentSaveStorage(file, ticketAttachment);
    }


    public ResponseEntity<Resource> getFile(UUID id){
        try {
            var attachment = attachmentRepository.findById(id).orElseThrow(() -> new BusinessException("File not found"));
            Path arquivo = Paths.get(path).resolve(attachment.getId().toString() +"/"+ attachment.getFilename()).normalize();

            Resource resource = new UrlResource(arquivo.toUri());
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(arquivo);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (Exception e) {
            throw new NotFoundException("File not found");
        }
    }

    public void ticketAttachmentSaveStorage(MultipartFile file, TicketAttachmentEntity ticket){
        try{
            if(file.getOriginalFilename().contains("..")){
                throw new FileStorageException("Sorry Filename contains invalida path sequence: "+file.getOriginalFilename());
            }
            var newPath = new File(path + ticket.getId() + "/");
            if(!newPath.exists()){
                newPath.mkdir();
            }

            Files.copy(file.getInputStream(), Path.of(newPath.getAbsolutePath() +"/"+ ticket.getFilename()), StandardCopyOption.REPLACE_EXISTING);

        }catch (IOException e){
            throw new FileStorageException("Error saving file: "+file.getOriginalFilename());
        }

    }
}
