package com.finstack.admin.assist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finstack.admin.assist.entity.Audit;

import java.util.UUID;

public interface AuditRepository extends JpaRepository<Audit, UUID> {
}
