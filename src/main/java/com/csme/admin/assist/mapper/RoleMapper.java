package com.csme.admin.assist.mapper;

import com.csme.admin.assist.entity.Role;
import com.csme.admin.assist.model.RoleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    Role RoleDTOToRole(RoleDTO roleDTO);
    RoleDTO RoleToRoleDTO(Role role);
    List<Role> RoleDTOsToRoles(List<RoleDTO> roleDTOs);
    List<RoleDTO> RolesToRoleDTOs(List<Role> roles);

}
