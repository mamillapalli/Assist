package com.csme.admin.assist.service;


import com.csme.admin.assist.model.ResourceDTO;

import java.util.List;

public interface ResourceService {
    List<ResourceDTO> getAll();

    ResourceDTO getResourceByEmailAddress(String emailAddress);

    ResourceDTO add(ResourceDTO resourceDTO);

    ResourceDTO update(ResourceDTO resourceDetails);
}
