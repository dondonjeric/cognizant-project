package com.academy.project.controller;

import com.academy.project.dto.UpdateAdminAndManagerRest;
import com.academy.project.exception.InvalidStringFormatException;
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
    @Autowired
    private CommunityAdminAndManagerService service;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/{id}")

    private ResponseEntity<UpdateAdminAndManagerRest> updateCommunityAdminAndManager(@RequestBody CommunityAdminAndManager manager, @PathVariable Long id) throws  RecordNotFoundException, InvalidStringFormatException {
        UpdateAdminAndManagerRest update =  modelMapper.map(service.updateCommunityManagerAndAdmin(manager,id), UpdateAdminAndManagerRest.class);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CommunityAdminAndManager> addCommunityAndAdminManager(@RequestBody CommunityAdminAndManager manager) throws InvalidStringFormatException {
        return new ResponseEntity<>(service.addCommunityAdminAndManager(manager), HttpStatus.CREATED);
    }

}
