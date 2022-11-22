package com.academy.project.controller;

import com.academy.project.dto.UpdateAdminAndManagerRest;
import com.academy.project.exception.InvalidStringFormatException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunityAdminAndManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/community/manager")
public class CommunityAdminAndManagerController {
    @Autowired
    private CommunityAdminAndManagerService service;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/{id}")
    private UpdateAdminAndManagerRest updateCommunityAdminAndManager(@RequestBody CommunityAdminAndManager manager, @PathVariable Long id) throws RecordNotFoundException, InvalidStringFormatException, RecordNotFoundException, InvalidStringFormatException {
        UpdateAdminAndManagerRest update =  modelMapper.map(service.updateComManager(manager,id), UpdateAdminAndManagerRest.class);
        return update;
    }
    @PostMapping
    public CommunityAdminAndManager addCommunityAndAdminManager(@RequestBody CommunityAdminAndManager manager) throws InvalidStringFormatException {
        return service.addComManager(manager);
    }
}
