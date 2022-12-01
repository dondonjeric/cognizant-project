package com.academy.project.repository;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.model.CommunityAdminAndManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityAdminAndManagerRepository extends JpaRepository<CommunityAdminAndManager, Long> {

    Optional<String> findByEmail(String email);
    Optional<String> findByCognizantId(String cognizantId);
    @Query(value = "Select * from communityadminandmanager WHERE isactive = TRUE LIMIT ?1 OFFSET ?2 ", nativeQuery = true)
    List<CommunityAdminAndManager> getAllActiveCommunityAdminAndManager(Integer size, Integer offset);
}
