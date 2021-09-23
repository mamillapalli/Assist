package com.csme.admin.assist.mapper;

import com.csme.admin.assist.entity.Resource;
import com.csme.admin.assist.model.ResourceDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ResourceMapper {

    ResourceDTO ResourceToResourceDTO(Resource resource);
    Resource ResourceDTOToResource(ResourceDTO resourceDTO);
    List<ResourceDTO> ResourcesToResourceDTOs(List<Resource> resources);
    List<Resource> ResourceDTOsToResources(List<ResourceDTO> resourceDTOs);

}
