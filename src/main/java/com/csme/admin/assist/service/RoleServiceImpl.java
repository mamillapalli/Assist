package com.csme.admin.assist.service;

import com.csme.admin.assist.mapper.RoleMapper;
import com.csme.admin.assist.model.RoleDTO;
import com.csme.admin.assist.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<RoleDTO> getAll() {
        return roleMapper.RolesToRoleDTOs(roleRepository.findAll());
    }
}
