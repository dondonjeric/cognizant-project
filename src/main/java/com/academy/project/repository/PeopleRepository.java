package com.academy.project.repository;

import com.academy.project.model.Community;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.model.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PeopleRepository extends JpaRepository<People,Long> {
    List<People> findByCommunityadminandmanageridAndIsactive(Long id, Boolean isactive);
}
