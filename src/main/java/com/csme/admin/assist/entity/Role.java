package com.csme.admin.assist.entity;

import com.csme.admin.assist.entity.Resource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ROLE_TABLE", schema = "ADMIN")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @Column (name="ROLE_ID")
    private UUID id;
    @Column (name="ROLE_NAME")
    @Size(min = 2 , message = "name of the role should be atleast 2 characters")
    private String name;
}
