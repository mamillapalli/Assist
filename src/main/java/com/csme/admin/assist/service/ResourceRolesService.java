package com.csme.admin.assist.service;

import com.csme.admin.assist.model.ResourceRolesDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResourceRolesService {
    List<ResourceRolesDTO> getRolesOfAllResources();

    ResourceRolesDTO assignRolesToResource(ResourceRolesDTO resourceRolesDTO);

    ResourceRolesDTO modifyResourceRoles(ResourceRolesDTO resourceRolesDTO);
}
