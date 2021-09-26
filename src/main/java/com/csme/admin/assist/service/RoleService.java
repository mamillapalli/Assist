package com.csme.admin.assist.service;

import com.csme.admin.assist.entity.Role;
import com.csme.admin.assist.model.ModifyRoleDTO;
import com.csme.admin.assist.model.RoleDTO;

import java.util.List;

public interface RoleService {


    public List<RoleDTO> getAll();

    RoleDTO add(RoleDTO roleDTO);
    RoleDTO update(ModifyRoleDTO roleDTO);
}
