package com.finstack.admin.assist.service;


import java.util.List;

import com.finstack.admin.assist.model.ResourceDTO;

public interface ResourceService {
    List<ResourceDTO> getAll();

    ResourceDTO getResourceByEmailAddress(String emailAddress);

    ResourceDTO add(ResourceDTO resourceDTO);

    ResourceDTO update(ResourceDTO resourceDetails);

    List<ResourceDTO> getResourcesByRole(String roleName);
}
