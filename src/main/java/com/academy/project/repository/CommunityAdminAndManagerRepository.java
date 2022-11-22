package com.academy.project.repository;

import com.academy.project.model.CommunityAdminAndManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityAdminAndManagerRepository extends JpaRepository<CommunityAdminAndManager, Long> {
}
