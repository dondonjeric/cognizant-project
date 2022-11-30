package com.academy.project.service;

import com.academy.project.dto.GetAllActiveCommunityAdminAndManagerRest;
import com.academy.project.exception.IllegalArgumentException;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.helper.SamplePageRequest;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.academy.project.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.PersistenceContext;
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
    public List<CommunityAdminAndManager> getAllActiveCommunityAdminAndManager(int offset, int limit) throws IllegalArgumentException {
        //return communityAdminAndManager.createQuery("SELECT * from communityadminandmanager WHERE is_active=true", GetAllActiveCommunityAdminAndManagerRest.class).setMaxResults(size).getResultList();

        Pageable pageable = new SamplePageRequest(offset, limit, Sort.unsorted());
        return repository.findAll(pageable).getContent();
        //        Pageable pageable = PageRequest.of(offset, size, Sort.DEFAULT_DIRECTION);
//        default List<CommunityAdminAndManager> adminAndManagers = repository.findAll(pageable).stream().filter(CommunityAdminAndManager::getIsactive).collect(Collectors.toList());
//        return new PageImpl<>(adminAndManagers);
    }

//    @Override
//    public List<CommunityAdminAndManager> getAll() {
//        Pageable pageable = new SamplePageRequest(0, 1, null);
//        return new;
//    }
}
