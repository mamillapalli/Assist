package com.csme.admin.assist.role;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {

    @Autowired
    RoleRepository roleRepository;


    @GetMapping(path="/roles")
    public List<Role> getAllRoles()
    {
        return roleRepository.findAll();
    }

    @GetMapping(path="/roles/{id}")
    public Optional<Role> getAllRoles(@PathVariable @Valid int id) throws Exception {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()) return role;

        throw new Exception("Role with id-->" + id + " not found");
    }

    @PostMapping(path = "/roles")
    public Role saveRole(@Valid @RequestBody Role role)
    {
        Role createdRole = roleRepository.save(role);
        return createdRole;
    }

    @PutMapping(path = "/roles/{id}")
    public ResponseEntity<Role> saveRole(@PathVariable(value = "id") Integer id, @Valid @RequestBody Role roleDetails) throws Exception {
        Role role = roleRepository.findById(id).orElseThrow(() -> new Exception("role wit id -> " + id + " not found"));
        role.setName(roleDetails.getName());
        final Role updatedRole = roleRepository.save(role);
        return ResponseEntity.ok(updatedRole);
    }

}
