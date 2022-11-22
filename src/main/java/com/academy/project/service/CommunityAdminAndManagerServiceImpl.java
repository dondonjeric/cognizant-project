package com.academy.project.service;

import com.academy.project.exception.InvalidStringFormatException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityAdminAndManagerServiceImpl implements CommunityAdminAndManagerService {

    @Autowired
    private CommunityManagerRepository repository;
    //private static final String NAME =  "[a-zA-Z-, .Ññ]{2,20}";

    @Override
    public CommunityAdminAndManager updateComManager(CommunityAdminAndManager updateComManager, Long id) throws InvalidStringFormatException, RecordNotFoundException {
        CommunityAdminAndManager comManager = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("This community manager is not in the record!"));

        return repository.save(comManager);
    }

    @Override
    public CommunityAdminAndManager addComManager(CommunityAdminAndManager comManager) throws InvalidStringFormatException {
        return repository.save(comManager);
    }
}
