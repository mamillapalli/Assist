package com.csme.admin.assist.repository;

import com.csme.admin.assist.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, UUID>{

    Optional<Resource> findByEmailAddress(String email);

    @Query("SELECT r FROM Resource r JOIN r.roles ro WHERE ro.name = :roleName")
    List<Resource> findByRoleName(@Param("roleName") String roleName);

}
