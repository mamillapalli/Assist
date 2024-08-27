package com.finstack.admin.assist.service;

import org.springframework.http.ResponseEntity;

import com.finstack.admin.assist.model.ResourceRolesDTO;

import java.util.List;

public interface ResourceRolesService {
    List<ResourceRolesDTO> getRolesOfAllResources();

    ResourceRolesDTO assignRolesToResource(ResourceRolesDTO resourceRolesDTO);

    ResourceRolesDTO modifyResourceRoles(ResourceRolesDTO resourceRolesDTO);
}
