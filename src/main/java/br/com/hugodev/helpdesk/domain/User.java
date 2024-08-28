package br.com.hugodev.helpdesk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private UUID id;
    private String username;
    private String password;
    private String name;
    private String email;
    private Boolean isActive;
    private Date createdAt;
    private UUID createdBy;
    private Date updateAt;
    private UUID updateBy;

}
