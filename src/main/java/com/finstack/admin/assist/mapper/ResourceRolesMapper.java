package com.finstack.admin.assist.mapper;

import org.mapstruct.Mapper;

import com.finstack.admin.assist.entity.Resource;
import com.finstack.admin.assist.model.ResourceRolesDTO;

import java.util.List;

@Mapper
public interface ResourceRolesMapper {

    ResourceRolesDTO ResourceToResourceRolesDTO(Resource resource);
    Resource ResourceRolesDTOToResource(ResourceRolesDTO resourceRolesDTO);
    List<ResourceRolesDTO> ResourcesToResourceRolesDTOs(List<Resource> resources);
    List<Resource> ResourceRolesDTOsToResources(List<ResourceRolesDTO> resourceRolesDTOs);

}
