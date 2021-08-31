package com.csme.admin.assist.resourcerole;


import com.csme.admin.assist.resource.Resource;
import com.csme.admin.assist.resource.ResourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Valid
@Slf4j
public class ResourceRoleController {

    @Autowired
    ResourceRoleRepository resourceRoleRepository;

    @GetMapping(path="/resourceRoles", name="getAllResourceRoles")
    public List<ResourceRole> getAllResourceRoles()
    {
        return resourceRoleRepository.findAll();
    }

    @GetMapping(path="/resourceRoles/{id}" ,name="getResourceRolesById")
    public List<ResourceRole> getResourceRolesById(@PathVariable int id)
    {
        List<ResourceRole> resourceRoles = resourceRoleRepository.findByResourceId(id);
        return resourceRoles;
    }


    @PostMapping(path="/resourceRoles/{id}" ,name="saveResourceRolesById")
    public List<ResourceRole> saveResourceRolesById(@PathVariable int id, @RequestBody List<ResourceRole> resourceRoles)
    {


        resourceRoles.stream().forEach((resourceRole) -> resourceRole.setResourceId(id));
        return resourceRoleRepository.saveAll(resourceRoles);

    }

}
