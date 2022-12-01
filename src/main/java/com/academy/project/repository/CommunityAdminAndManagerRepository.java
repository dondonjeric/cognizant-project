package com.academy.project.repository;

import com.academy.project.model.CommunityAdminAndManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityAdminAndManagerRepository extends JpaRepository<CommunityAdminAndManager, Long> {


    Optional<CommunityAdminAndManager> findByEmailAndIsactive(String email,Boolean isactive);
    Optional<CommunityAdminAndManager> findByCognizantIdAndIsactive(String cognizantId,Boolean isactive);

}
