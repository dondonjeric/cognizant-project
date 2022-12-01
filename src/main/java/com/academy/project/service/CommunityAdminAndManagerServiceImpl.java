package com.academy.project.service;

import com.academy.project.exception.InvalidDeleteException;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.Community;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.model.People;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.academy.project.repository.CommunityRepository;
import com.academy.project.repository.PeopleRepository;
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
    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private PeopleRepository peopleRepository;

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
//    delete with validation
//    @Override
//    public void deleteCommunityManagerAndAdmin(Long id) throws RecordNotFoundException, InvalidDeleteException {
//        CommunityAdminAndManager communityAdminAndManager = repository.findById(id)
//                .orElseThrow(() -> new RecordNotFoundException("Record not found"));
//        if (!communityAdminAndManager.getIsactive()) {
//            throw new RecordNotFoundException("Record not found");
//        }
//        //  List<Community> communityList = communityRepository.findAll().stream().filter(community -> community.getMgrid() == id && community.getIsactive() == true).toList();
//        List<Community> communityList = communityRepository.findByMgridAndIsactive(id, true);
//        if (communityList.size() > 0)
//            throw new InvalidDeleteException("Can't delete this record, Please update the community table first before deleting.");
//        //List<People> peopleList = peopleRepository.findAll().stream().filter(people -> people.getCommunityadminandmanagerid() == id && people.getIsactive() == true).toList();
//        List<People> peopleList = peopleRepository.findByCommunityadminandmanageridAndIsactive(id, true);
//        if (peopleList.size() > 0)
//            throw new InvalidDeleteException("Can't delete this record, Please update the people table first before deleting.");
//
//        communityAdminAndManager.setIsactive(false);
//        repository.save(communityAdminAndManager);
//    }


//    delete with update
//    @Override
//    public void deleteCommunityManagerAndAdmin(Long id) throws RecordNotFoundException {
//        CommunityAdminAndManager communityAdminAndManager = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found!"));
//        if (!communityAdminAndManager.getIsactive()) {
//            throw new RecordNotFoundException("Record not found!");
//        }
//        communityAdminAndManager.setIsactive(false);
//
//        List<Community> communityList = communityRepository.findAll().stream()
//                .filter(community -> community.getMgrid() == communityAdminAndManager.getId()).toList();
//        communityList.forEach(community -> community.setMgrid(null));
//
//        List<People> peopleList = peopleRepository.findAll()
//                .stream().filter(people -> people.getCommunityadminandmanagerid() == communityAdminAndManager.getId()).toList();
//        peopleList.forEach(people -> people.setCommunityadminandmanagerid(null));
//
//        peopleRepository.saveAll(peopleList);
//        repository.save(communityAdminAndManager);
//        communityRepository.saveAll(communityList);
//    }

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
