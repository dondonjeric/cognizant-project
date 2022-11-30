package com.academy.project.service;


import com.academy.project.exception.IllegalArgumentException;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunityAdminAndManagerService {

    CommunityAdminAndManager addCommunityAdminAndManager(CommunityAdminAndManager CommunityAdminAndManager) throws InvalidInputException, RecordNotFoundException;
    CommunityAdminAndManager updateCommunityManagerAndAdmin(CommunityAdminAndManager CommunityAdminAndManager) throws RecordNotFoundException, InvalidInputException;
    List<CommunityAdminAndManager> getAllActiveCommunityAdminAndManager(int offset, int limit) throws IllegalArgumentException;

//    @Query(value = "SELECT * FROM communityadminandmanager",nativeQuery = true)
//    List<CommunityAdminAndManager> getAll();

}
