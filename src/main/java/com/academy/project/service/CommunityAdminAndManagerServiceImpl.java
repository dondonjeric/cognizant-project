package com.academy.project.service;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.academy.project.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Page<CommunityAdminAndManager> getAllActiveCommunityAdminAndManager(Pageable pageable){
        List<CommunityAdminAndManager> adminAndManagers = repository.findAll(pageable).stream().filter(CommunityAdminAndManager::getIsactive).collect(Collectors.toList());
        return new PageImpl<>(adminAndManagers);
    }

    @Override
    public Page<CommunityAdminAndManager> defaultGetAllActiveCommunityAdminAndManager(Pageable pageable) {
        List<CommunityAdminAndManager> adminAndManagers = repository.findAll(pageable).stream().filter(CommunityAdminAndManager::getIsactive).collect(Collectors.toList());
        return new PageImpl<>(adminAndManagers);
    }
//    @Override
//    public Page<CommunityAdminAndManager> defaultGetAllActiveCommunityAdminAndManager(Pageable pageable){
//        List<CommunityAdminAndManager> adminAndManagers = repository.findAll(pageable).stream().filter(CommunityAdminAndManager::getIsactive).collect(Collectors.toList());
//        return new PageImpl<>(adminAndManagers);
//    }

}
