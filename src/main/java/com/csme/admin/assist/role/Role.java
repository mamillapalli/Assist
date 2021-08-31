package com.csme.admin.assist.role;

import com.csme.admin.assist.resource.Resource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "ROLE_TABLE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue
    @Column (name="ROLE_ID")
    private int id;
    @Column (name="ROLE_NAME")
    @Size(min = 2 , message = "name of the role should be atleast 2 characters")
    private String name;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<Resource> resources;
}
