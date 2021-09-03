package com.csme.admin.assist.resource;


import com.csme.admin.assist.resourcerole.ResourceRoleController;
import com.csme.admin.assist.resourcerole.ResourceRoleRepository;
import com.csme.admin.assist.role.RoleController;
import com.csme.admin.assist.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Valid
public class ResourceController {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ResourceRoleRepository resourceRoleRepository;

    @Autowired
    RoleRepository roleRepository;


    // TO GET ALL RESOURCES FOR ADMIN USERS
    @GetMapping(path="/resources", name="getAllResources")
    public List<Resource> getAllResources()
    {
        return resourceRepository.findAll();
    }


    //TO GET RESOURCE DETAILS FOR ADMIN USERS
    @GetMapping(path="/resources/{id}" ,name="getResourceById")
    public Optional<Resource> getResourceById(@PathVariable long id)
    {
        Optional<Resource> resource = resourceRepository.findById(id);
        if(resource.isPresent()) return resource;
        else throw new ResourceNotFoundException("Resource wit id -> " + id + " not found");
    }

    //TO ADD A RESOURCE FOR ADMIN USERS
    @PostMapping(path="/resources", name="saveResource")
    public Resource saveResource(@Valid  @RequestBody Resource resource)
    {
        Resource createdResource = resourceRepository.save(resource);
        return createdResource;
    }

    //TO MODIFY A RESOURCE FOR ADMIN USERS
    @PutMapping(path="/resources/{id}", name="updateResource")
    public ResponseEntity<Resource> updateResource(@PathVariable(value = "id") Long id, @Valid @RequestBody Resource resourceDetails)
    {
        Resource resource = resourceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource wit id -> " + id + " not found"));

        resource.setBirthDate(resourceDetails.getBirthDate());
        resource.setApproverId(resourceDetails.getApproverId());
        resource.setFirstName(resourceDetails.getFirstName());
        resource.setLastName(resourceDetails.getLastName());
        resource.setJoiningDate(resourceDetails.getJoiningDate());
        resource.setStatus(resourceDetails.isStatus());
        final Resource updatedResource = resourceRepository.save(resource);
        return ResponseEntity.ok(updatedResource);
    }

}
