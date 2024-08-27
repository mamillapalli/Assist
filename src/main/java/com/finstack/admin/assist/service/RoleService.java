package com.finstack.admin.assist.service;

import java.util.List;

import com.finstack.admin.assist.entity.Role;
import com.finstack.admin.assist.model.ModifyRoleDTO;
import com.finstack.admin.assist.model.RoleDTO;

public interface RoleService {


    public List<RoleDTO> getAll();

    RoleDTO add(RoleDTO roleDTO);
    RoleDTO update(ModifyRoleDTO roleDTO);
}
