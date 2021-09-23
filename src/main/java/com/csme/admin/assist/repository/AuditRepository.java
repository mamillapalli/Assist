package com.csme.admin.assist.repository;

import com.csme.admin.assist.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditRepository extends JpaRepository<Audit, UUID> {
}
