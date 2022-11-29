package com.academy.project.service;

import com.academy.project.controller.CommunityAdminAndManagerController;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommunityAdminAndManagerService {

    CommunityAdminAndManager addCommunityAdminAndManager(CommunityAdminAndManager CommunityAdminAndManager) throws InvalidInputException, RecordNotFoundException;
    CommunityAdminAndManager updateCommunityManagerAndAdmin(CommunityAdminAndManager CommunityAdminAndManager) throws RecordNotFoundException, InvalidInputException;
    Page<CommunityAdminAndManager> getAllAdminAndManager(Pageable pageable);
    void deleteCommunityManagerAndAdmin(Long id ) throws RecordNotFoundException;
}
