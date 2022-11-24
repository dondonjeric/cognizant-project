package com.academy.project.controller;

import com.academy.project.dto.CreateCommunityAdminAndManagerRest;
import com.academy.project.dto.UpdateCommunityAdminAndManagerRest;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunityAdminAndManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/community/manager")
public class CommunityAdminAndManagerController {

    private CommunityAdminAndManagerService service;

    private ModelMapper modelMapper;
    @Autowired
    public CommunityAdminAndManagerController(CommunityAdminAndManagerService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCommunityAdminAndManager(@RequestBody UpdateCommunityAdminAndManagerRest updateManager, @PathVariable Long id) throws  RecordNotFoundException, InvalidInputException {
        updateManager.setId(id);
        CommunityAdminAndManager manager = modelMapper.map(updateManager, CommunityAdminAndManager.class);
        service.updateCommunityManagerAndAdmin(manager);
        return new ResponseEntity<>("Successfully updated!", HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addCommunityAndAdminManager(@RequestBody CreateCommunityAdminAndManagerRest create) throws InvalidInputException, RecordNotFoundException {
        CommunityAdminAndManager manager = modelMapper.map(create, CommunityAdminAndManager.class);
        service.addCommunityAdminAndManager(manager);
        return new ResponseEntity<>("Successfully registered!", HttpStatus.OK);
    }

}
