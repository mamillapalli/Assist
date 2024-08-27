package com.finstack.admin.assist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finstack.admin.assist.entity.Resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, UUID>{

    Optional<Resource> findByEmailAddress(String email);

    @Query("SELECT r FROM Resource r JOIN r.roles ro WHERE ro.name = :roleName")
    List<Resource> findByRoleName(@Param("roleName") String roleName);

}
