package com.csme.admin.assist.repository;

import com.csme.admin.assist.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, UUID>{

    Optional<Resource> findByEmailAddress(String email);
}
