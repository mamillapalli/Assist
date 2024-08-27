package com.finstack.admin.assist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finstack.admin.assist.entity.Resource;

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
public class Role extends Base{

    @Id
    @Column (name="ROLE_ID")
    private UUID id;
    @Column (name="ROLE_NAME")
    @Size(min = 2 , message = "name of the role should be atleast 2 characters")
    private String name;
    @Column (name="ROLE_DESC")
    private String roleDesc;

}
