package com.academy.project.service;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.academy.project.validation.Validator;
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
    public CommunityAdminAndManager addCommunityAdminAndManager(CommunityAdminAndManager comManager) throws InvalidInputException, RecordNotFoundException {
        validator.checkCreateIfValid(comManager);
        return repository.save(comManager);
    }

    @Override
    public CommunityAdminAndManager updateCommunityManagerAndAdmin(CommunityAdminAndManager updateComManager) throws InvalidInputException, RecordNotFoundException {
        validator.checkUpdateIfValid(updateComManager);
        CommunityAdminAndManager manager = repository.findById(updateComManager.getId()).get();
        manager.setName(updateComManager.getName());
        return repository.save(manager);
    }

    @Override
    public List<CommunityAdminAndManager> getAllActiveCommunityAdminAndManager(Integer size, Integer offset) throws InvalidInputException {
        if(size == null && offset == null){
            return repository.findAll().stream().filter(CommunityAdminAndManager::getIsactive).toList();
        }
        validator.checkFilter(size,offset);
        return repository.getAllActiveCommunityAdminAndManager(size, offset);
    }
}
