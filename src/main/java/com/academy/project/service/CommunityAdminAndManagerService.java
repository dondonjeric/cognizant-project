package com.academy.project.service;

import com.academy.project.model.CommunityAdminAndManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface CommunityAdminAndManagerService {

    CommunityAdminAndManager addCommunityAdminAndManager(CommunityAdminAndManager CommunityAdminAndManager);
    CommunityAdminAndManager updateCommunityManagerAndAdmin(CommunityAdminAndManager CommunityAdminAndManager);
    void deleteCommunityManagerAndAdmin(Long id ) throws RecordNotFoundException;
    List<CommunityAdminAndManager> getAllActiveCommunityAdminAndManager(Integer size, Integer offset);
    Long count();
}
