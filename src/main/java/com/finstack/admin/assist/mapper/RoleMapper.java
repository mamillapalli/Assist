package com.finstack.admin.assist.mapper;

import org.mapstruct.Mapper;

import com.finstack.admin.assist.entity.Role;
import com.finstack.admin.assist.model.RoleDTO;

import java.util.List;

@Mapper
public interface RoleMapper {

    Role RoleDTOToRole(RoleDTO roleDTO);
    RoleDTO RoleToRoleDTO(Role role);
    List<Role> RoleDTOsToRoles(List<RoleDTO> roleDTOs);
    List<RoleDTO> RolesToRoleDTOs(List<Role> roles);

}
