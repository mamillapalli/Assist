package com.csme.admin.assist.service;

import com.csme.admin.assist.entity.Resource;
import com.csme.admin.assist.mapper.ResourceMapper;
import com.csme.admin.assist.model.ResourceDTO;
import com.csme.admin.assist.repository.ResourceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public List<ResourceDTO> getAll() {
        if(resourceRepository.findAll().isEmpty()) throw new RuntimeException("Resource List Empty");
        return resourceMapper.ResourcesToResourceDTOs(resourceRepository.findAll());
    }

    @Override
    public ResourceDTO getResourceByEmailAddress(String emailAddress) {
        return resourceMapper.ResourceToResourceDTO(resourceRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new ResourceNotFoundException("Resource with email " + emailAddress + " not found")));
    }

    @Override
    public ResourceDTO add(ResourceDTO resourceDTO) {
        if(resourceRepository.findByEmailAddress(resourceDTO.getEmailAddress()).isPresent())
            throw new ResourceNotFoundException("Resource with email " + resourceDTO.getEmailAddress() + " already exists");
        Resource resource = resourceMapper.ResourceDTOToResource(resourceDTO);
        resource.setUuid(UUID.randomUUID());

        //TO RETRIEVE USER NAME FROM JWT
        resource.setCreationDetails("RAVIKANTH");
        return resourceMapper.ResourceToResourceDTO(resourceRepository.save(resource));
        //return resourceDTO;
    }

    @Override
    public ResourceDTO update(ResourceDTO resourceDetails) {

        Resource resource = resourceRepository.findByEmailAddress(resourceDetails.getEmailAddress()).orElseThrow(() ->
                new ResourceNotFoundException("Resource with email  -> " + resourceDetails.getEmailAddress() + " not found"));
        BeanUtils.copyProperties(resourceDetails,resource);
        // TO RETRIEVE USER NAME FROM JWT
        resource.setModificationDetails("RAVIKANTH");
        return resourceMapper.ResourceToResourceDTO(resourceRepository.save(resource));
    }
}
