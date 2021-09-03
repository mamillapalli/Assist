package com.csme.admin.assist.role;

import com.csme.admin.assist.resource.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

    Role getByName(String name);
}
