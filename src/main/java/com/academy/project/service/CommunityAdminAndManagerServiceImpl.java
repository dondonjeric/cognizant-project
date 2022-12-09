package com.academy.project.service;

import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.academy.project.helper.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityAdminAndManagerServiceImpl implements CommunityAdminAndManagerService {

    @Autowired
    private CommunityAdminAndManagerRepository repository;

    @Autowired
    private Validator validator;

    @Override
    public CommunityAdminAndManager addCommunityAdminAndManager(CommunityAdminAndManager comManager) {
        validator.checkCreateIfValid(comManager);
        return repository.save(comManager);
    }

    @Override
    public CommunityAdminAndManager updateCommunityManagerAndAdmin(CommunityAdminAndManager updateComManager){
        validator.checkUpdateIfValid(updateComManager);
        CommunityAdminAndManager manager = repository.findById(updateComManager.getId()).get();
        manager.setName(updateComManager.getName());

        return repository.save(manager);
    }

    @Override
    public void deleteCommunityManagerAndAdmin(Long id) {
        CommunityAdminAndManager communityAdminAndManager = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Record not found"));
        if (!communityAdminAndManager.getIsactive()) {
            throw new RecordNotFoundException("Record not found");
        }
        communityAdminAndManager.setIsactive(false);
        repository.save(communityAdminAndManager);
    }
    @Override
    public List<CommunityAdminAndManager> getAllActiveCommunityAdminAndManager(Integer size, Integer offset){
        if(size == null && offset == null){
            return repository.getAllIsActive();
        }
        validator.checkFilter(size,offset);
        return repository.getAllActiveCommunityAdminAndManager(size, offset);
    }

    @Override
    public Long count() {
        return repository.counts();
    }
}
