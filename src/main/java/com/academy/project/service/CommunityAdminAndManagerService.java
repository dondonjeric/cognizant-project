package com.academy.project.service;

import com.academy.project.exception.InvalidStringFormatException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;

public interface CommunityAdminAndManagerService {

    CommunityAdminAndManager addCommunityAdminAndManager(CommunityAdminAndManager CommunityAdminAndManager) throws InvalidStringFormatException;
    CommunityAdminAndManager updateCommunityManagerAndAdmin(CommunityAdminAndManager CommunityAdminAndManager, Long id) throws RecordNotFoundException, InvalidStringFormatException;

}
