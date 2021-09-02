package com.adam.auditservice.repository;


import com.adam.auditservice.entity.Audit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditRepository extends MongoRepository<Audit,String> {
}
