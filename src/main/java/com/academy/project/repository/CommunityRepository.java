package com.academy.project.repository;

import com.academy.project.model.Community;
import com.academy.project.model.CommunityAdminAndManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long>{
    List<Community> findByMgridAndIsactive(Long id, Boolean isactive);

}
