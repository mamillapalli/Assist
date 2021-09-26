package com.csme.admin.assist.service;

import com.csme.admin.assist.entity.Resource;
import com.csme.admin.assist.entity.Role;
import com.csme.admin.assist.mapper.ResourceRolesMapper;
import com.csme.admin.assist.model.ResourceRolesDTO;
import com.csme.admin.assist.repository.ResourceRepository;
import com.csme.admin.assist.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ResourceRolesServiceImpl implements ResourceRolesService {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ResourceRolesMapper resourceRolesMapper;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<ResourceRolesDTO> getRolesOfAllResources() {

        return resourceRolesMapper.ResourcesToResourceRolesDTOs(resourceRepository.findAll());
    }

    @Override
    public ResourceRolesDTO assignRolesToResource(ResourceRolesDTO resourceRolesDTO) {
        if(resourceRepository.findByEmailAddress(resourceRolesDTO.getEmailAddress()).isEmpty())
            throw new ResourceNotFoundException("Resource with " + resourceRolesDTO.getEmailAddress() + " does not exist");
        Resource newResource = resourceRepository.findByEmailAddress(resourceRolesDTO.getEmailAddress()).get();
        List<Role> newRoles = new ArrayList<>();
        resourceRolesDTO.getRoles().forEach((role)->{
            if(roleRepository.getByName(role.getName())==null) throw new ResourceNotFoundException("Role " + role.getName() + "not found");
           Role roleDetails = roleRepository.getByName(role.getName());
           newRoles.add(roleDetails);
        });
        newResource.setRoles(newRoles);
        return resourceRolesMapper.ResourceToResourceRolesDTO(resourceRepository.save(newResource));

    }

    @Override
    public ResourceRolesDTO modifyResourceRoles(ResourceRolesDTO resourceRolesDTO) {
        if(resourceRepository.findByEmailAddress(resourceRolesDTO.getEmailAddress()).isEmpty())
            throw new ResourceNotFoundException("Resource with " + resourceRolesDTO.getEmailAddress() + " does not exist");
        Resource newResource = resourceRepository.findByEmailAddress(resourceRolesDTO.getEmailAddress()).get();
        List<Role> newRoles = new ArrayList<>();
        resourceRolesDTO.getRoles().forEach((role)->{
            if(roleRepository.getByName(role.getName())==null) throw new ResourceNotFoundException("Role " + role.getName() + "not found");
            Role roleDetails = roleRepository.getByName(role.getName());
            newRoles.add(roleDetails);
        });
        newResource.setRoles(newRoles);
        return resourceRolesMapper.ResourceToResourceRolesDTO(resourceRepository.save(newResource));

    }
}
