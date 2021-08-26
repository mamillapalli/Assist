package com.csme.admin.assist.resource;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResourceController {

    @AutoConfigureOrder
    ResourceRepository resourceRepository;

    @GetMapping(name = "\resources")
    public List<Resource> getAllResources()
    {
        return resourceRepository.findAll();
    }
}
