package com.academy.project.service;


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

    Page<CommunityAdminAndManager> getAllActiveCommunityAdminAndManager(Pageable pageable);
    Page<CommunityAdminAndManager> defaultGetAllActiveCommunityAdminAndManager(Pageable pageable);

}
