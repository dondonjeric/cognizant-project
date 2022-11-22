package com.academy.project.service;

import com.academy.project.exception.InvalidStringFormatException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;

public interface CommunityAdminAndManagerService {
    CommunityAdminAndManager updateComManager(CommunityAdminAndManager comManager, Long id) throws RecordNotFoundException, InvalidStringFormatException;
    CommunityAdminAndManager addComManager(CommunityAdminAndManager comManager) throws InvalidStringFormatException;
}
