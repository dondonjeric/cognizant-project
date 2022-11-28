package com.academy.project.service;

import com.academy.project.dto.GetActiveCommunityAdminAndManagerRest;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.ByteArrayInputStream;

public interface CommunityAdminAndManagerService {

    CommunityAdminAndManager addCommunityAdminAndManager(CommunityAdminAndManager CommunityAdminAndManager) throws InvalidInputException, RecordNotFoundException;
    CommunityAdminAndManager updateCommunityManagerAndAdmin(CommunityAdminAndManager CommunityAdminAndManager) throws RecordNotFoundException, InvalidInputException;

    public ByteArrayInputStream downloadExcel();

    Page<CommunityAdminAndManager> getAllActiveManagerAndAdmin(Pageable pageable);

    CommunityAdminAndManager deleteCommunityManagerAndAdmin(CommunityAdminAndManager communityAdminAndManager) throws RecordNotFoundException, IllegalArgumentException;




}
