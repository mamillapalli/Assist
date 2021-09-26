package com.csme.admin.assist.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ResourceRolesDTO {

    @NotEmpty
    private String emailAddress;
    private List<RoleDTO> roles;
}
