package com.csme.admin.assist.resource;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ResourceController {

    @AutoConfigureOrder
    ResourceRepository resourceRepository;

    @GetMapping(name = "/resources")
    public List<Resource> getAllResources()
    {
        return resourceRepository.findAll();
    }

    @PostMapping(name = "/resources")
    public Resource saveResource(@Valid  @RequestBody Resource resource)
    {
        return resourceRepository.save(resource);
    }

    @PutMapping(name = "/resources/{id}")
    public ResponseEntity<Resource> saveResource(@PathVariable(value = "id") Long id, @Valid @RequestBody Resource resourceDetails)
    {
        Resource resource = resourceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource wit id -> " + id + " not found"));
        resource.setAdmin(resourceDetails.isAdmin());
        resource.setApprover(resourceDetails.isApprover());
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
