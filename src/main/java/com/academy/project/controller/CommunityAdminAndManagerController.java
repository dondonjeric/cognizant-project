package com.academy.project.controller;

import com.academy.project.dto.CommunityAdminAndManagerDTO;
import com.academy.project.dto.UpdateCommunityAdminAndManagerRest;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunityAdminAndManagerService;
import com.academy.project.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/community/manager")
public class CommunityAdminAndManagerController {
    @Autowired
    private CommunityAdminAndManagerService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Validator validator;

    @PutMapping("/{id}")
    private ResponseEntity<String> updateCommunityAdminAndManager(@RequestBody CommunityAdminAndManager manager, @PathVariable Long id) throws  RecordNotFoundException, InvalidInputException {
        //UpdateCommunityAdminAndManagerRest update =  modelMapper.map(service.updateCommunityManagerAndAdmin(manager,id), UpdateCommunityAdminAndManagerRest.class);
        validator.checkIfValidId(id);
        manager.setId(id);
        service.updateCommunityManagerAndAdmin(manager);
        return new ResponseEntity<>("Successfully updated!", HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addCommunityAndAdminManager(@RequestBody CommunityAdminAndManager manager) throws InvalidInputException, RecordNotFoundException {
        service.addCommunityAdminAndManager(manager);
        return new ResponseEntity<>("Successfully registered!", HttpStatus.CREATED);
    }

}
