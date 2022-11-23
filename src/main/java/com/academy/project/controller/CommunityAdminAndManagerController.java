package com.academy.project.controller;

import com.academy.project.dto.UpdateAdminAndManagerRest;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunityAdminAndManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/community/manager")
public class CommunityAdminAndManagerController {
    @Autowired
    private CommunityAdminAndManagerService service;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/{id}")
    private ResponseEntity<UpdateAdminAndManagerRest> updateCommunityAdminAndManager(@RequestBody CommunityAdminAndManager manager, @PathVariable Long id) throws  RecordNotFoundException, InvalidInputException {
        UpdateAdminAndManagerRest update =  modelMapper.map(service.updateCommunityManagerAndAdmin(manager,id), UpdateAdminAndManagerRest.class);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addCommunityAndAdminManager(@RequestBody CommunityAdminAndManager manager) throws InvalidInputException, RecordNotFoundException {
        service.addCommunityAdminAndManager(manager);
        return new ResponseEntity<>("Successfully registered!", HttpStatus.CREATED);
    }

}
