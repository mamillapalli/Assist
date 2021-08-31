package com.csme.admin.assist.resourcerole;

import com.csme.admin.assist.resource.Resource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "resource_roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResourceRole {

    @Id
    @GeneratedValue
    @Column (name="resource_roles_id")
    private int id;
    @Column(name="resource_id")
    private int resourceId;
    @Column(name="role_id")
    private int roleId;
}
