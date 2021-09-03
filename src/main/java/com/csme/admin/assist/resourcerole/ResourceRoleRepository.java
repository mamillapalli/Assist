package com.csme.admin.assist.resourcerole;

import com.csme.admin.assist.resource.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRoleRepository extends JpaRepository<ResourceRole, Integer>{

    List<ResourceRole> findByResourceId(int id);
    List<ResourceRole> findByRoleId(int id);
    void deleteByResourceId(int id);
}
