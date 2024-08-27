package com.finstack.admin.assist.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.finstack.admin.assist.entity.Role;
import com.finstack.admin.assist.mapper.RoleMapper;
import com.finstack.admin.assist.model.ModifyRoleDTO;
import com.finstack.admin.assist.model.RoleDTO;
import com.finstack.admin.assist.repository.RoleRepository;

import java.util.List;
import java.util.UUID;

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

    @Override
    public RoleDTO add(RoleDTO roleDTO) {
        if(roleRepository.getByName(roleDTO.getName())!=null) throw new ResourceNotFoundException("Role " + roleDTO.getName() + " already exists");
        Role role = roleMapper.RoleDTOToRole(roleDTO);
        role.setId(UUID.randomUUID());
        //TO GET USER NAME FROM jwt
        role.setCreationDetails("RAVIKANTH");
        return roleMapper.RoleToRoleDTO(roleRepository.save(role));
    }

    @Override
    public RoleDTO update(ModifyRoleDTO roleDTO) {
        if(roleRepository.getByName(roleDTO.getOldName())==null) throw new ResourceNotFoundException("Role " + roleDTO.getOldName() + " does not exist");
        Role role = roleRepository.getByName(roleDTO.getOldName());
        BeanUtils.copyProperties(roleDTO,role);
        //to get user name from jwt
        role.setModificationDetails("RAVIKANTH");
        return roleMapper.RoleToRoleDTO(roleRepository.save(role));

    }
}
