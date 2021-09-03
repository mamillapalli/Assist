package com.csme.admin.assist.resourcerole;


import com.csme.admin.assist.resource.Resource;
import com.csme.admin.assist.resource.ResourceRepository;
import com.csme.admin.assist.role.Role;
import com.csme.admin.assist.role.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Valid
@Slf4j
public class ResourceRoleController {

    @Autowired
    ResourceRoleRepository resourceRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ResourceRepository resourceRepository;


    // TO GET A LIST OF RESOURCES AND THEIR ROLES FOR ADMIN USERS
    @GetMapping(path="/resourceRoles", name="getAllResourceRoles")
    public List<ResourceRole> getAllResourceRoles()
    {
        return resourceRoleRepository.findAll();
    }

    // TO GET A LIST OF ROLES FOR A GIVEN RESOURCE ID FOR ADMIN USERS
    @GetMapping(path="/rolesForResource/{id}" ,name="getResourceRolesById")
    public List<ResourceRole> getResourceRolesById(@PathVariable int id)
    {
        List<ResourceRole> resourceRoles = resourceRoleRepository.findByResourceId(id);
        return resourceRoles;
    }

    // TO GET A LIST OF RESOURCES WITH ROLE FOR ADMIN USERS
    @GetMapping(path="/resourcesWithRole/{name}" ,name="getResourcesByRoleName")
    public  List<Optional<Resource>> getResourcesByRoleName(@PathVariable String name)
    {
        Role role = roleRepository.getByName(name);
        List<ResourceRole> resourceRoles = resourceRoleRepository.findByRoleId(role.getId());
        List<Optional<Resource>> resources = new ArrayList<>();
        for (ResourceRole resourceRole:resourceRoles
             ) {
            Optional<Resource> byId = resourceRepository.findById(Long.valueOf(String.valueOf(resourceRole.getResourceId())));
            if(byId.isPresent()) resources.add(byId);

        }
        return resources;
    }

    // TO ASSIGN ROLES TO RESOURCE FOR ADMIN USERS
    @PostMapping(path="/assignRolesForResource/{id}" ,name="saveResourceRolesById")
    @Transactional
    public List<ResourceRole> saveResourceRolesById(@PathVariable int id, @RequestBody List<ResourceRole> resourceRoles)
    {
        resourceRoles.stream().forEach((resourceRole) -> resourceRole.setResourceId(id));
        resourceRoleRepository.deleteByResourceId(id);
        return resourceRoleRepository.saveAll(resourceRoles);

    }

}
