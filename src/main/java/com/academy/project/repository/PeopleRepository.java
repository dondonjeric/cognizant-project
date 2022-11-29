package com.academy.project.repository;

import com.academy.project.model.Community;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.model.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People,Long> {

}
