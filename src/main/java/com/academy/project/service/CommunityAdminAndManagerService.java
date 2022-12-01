package com.academy.project.service;

import com.academy.project.model.CommunityAdminAndManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommunityAdminAndManagerService {

    CommunityAdminAndManager addCommunityAdminAndManager(CommunityAdminAndManager CommunityAdminAndManager);
    CommunityAdminAndManager updateCommunityManagerAndAdmin(CommunityAdminAndManager CommunityAdminAndManager);

    Page<CommunityAdminAndManager> getAllAdminAndManager(Pageable pageable);
    void deleteCommunityManagerAndAdmin(Long id );

    Page<CommunityAdminAndManager> getAllActiveCommunityAdminAndManager(Pageable pageable);
    Page<CommunityAdminAndManager> defaultGetAllActiveCommunityAdminAndManager(Pageable pageable);

}
