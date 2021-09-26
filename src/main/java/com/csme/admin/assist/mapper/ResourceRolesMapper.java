package com.csme.admin.assist.mapper;

import com.csme.admin.assist.entity.Resource;
import com.csme.admin.assist.model.ResourceRolesDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ResourceRolesMapper {

    ResourceRolesDTO ResourceToResourceRolesDTO(Resource resource);
    Resource ResourceRolesDTOToResource(ResourceRolesDTO resourceRolesDTO);
    List<ResourceRolesDTO> ResourcesToResourceRolesDTOs(List<Resource> resources);
    List<Resource> ResourceRolesDTOsToResources(List<ResourceRolesDTO> resourceRolesDTOs);

}
