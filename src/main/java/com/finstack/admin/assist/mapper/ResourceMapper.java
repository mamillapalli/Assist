package com.finstack.admin.assist.mapper;

import org.mapstruct.Mapper;

import com.finstack.admin.assist.entity.Resource;
import com.finstack.admin.assist.model.ResourceDTO;

import java.util.List;

@Mapper
public interface ResourceMapper {

    ResourceDTO ResourceToResourceDTO(Resource resource);
    Resource ResourceDTOToResource(ResourceDTO resourceDTO);
    List<ResourceDTO> ResourcesToResourceDTOs(List<Resource> resources);
    List<Resource> ResourceDTOsToResources(List<ResourceDTO> resourceDTOs);

}
