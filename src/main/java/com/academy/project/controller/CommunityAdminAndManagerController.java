package com.academy.project.controller;

import com.academy.project.dto.CreateCommunityAdminAndManagerRest;
import com.academy.project.dto.GetAllActiveCommunityAdminAndManagerRest;
import com.academy.project.dto.UpdateCommunityAdminAndManagerRest;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.helper.CustomPage;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunityAdminAndManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/community/manager")
public class CommunityAdminAndManagerController {
    @Autowired
    private CommunityAdminAndManagerService service;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/{id}")
    private ResponseEntity<String> updateCommunityAdminAndManager(@RequestBody UpdateCommunityAdminAndManagerRest updateManager, @PathVariable Long id) throws  RecordNotFoundException, InvalidInputException {
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
      @DeleteMapping("/{id}")
     public ResponseEntity<String> deleteCommunityManagerAndAdmin(@PathVariable Long id) throws RecordNotFoundException {
          service.deleteCommunityManagerAndAdmin(id);
          return new ResponseEntity<>("Successfully deleted!", HttpStatus.OK);
      }
    @GetMapping
    public CustomPage<GetAllActiveCommunityAdminAndManagerRest> getAllActiveCommunityAdminAndManager(@PathParam("offset")int offset, @PathParam("size")int size, Pageable pageable) {
        pageable = PageRequest.of(offset, size);
        Page<CommunityAdminAndManager> manager = service.getAllActiveCommunityAdminAndManager(pageable);
        List<GetAllActiveCommunityAdminAndManagerRest> getAllActive = manager.stream().map(communityAdminAndManager -> modelMapper.map(communityAdminAndManager, GetAllActiveCommunityAdminAndManagerRest.class)).toList();
        return new CustomPage<GetAllActiveCommunityAdminAndManagerRest>(new PageImpl<>(getAllActive));
    }
    @GetMapping("/default")
    public CustomPage<GetAllActiveCommunityAdminAndManagerRest>defaultGetAllActiveCommunityAdminAndManager(Pageable pageable) {
        Page<CommunityAdminAndManager> manager1 = service.defaultGetAllActiveCommunityAdminAndManager(pageable);
        List<GetAllActiveCommunityAdminAndManagerRest> getAllActive1 = manager1.stream().map(communityAdminAndManager -> modelMapper.map(communityAdminAndManager, GetAllActiveCommunityAdminAndManagerRest.class)).toList();
        return new CustomPage<GetAllActiveCommunityAdminAndManagerRest>(new PageImpl<>(getAllActive1));

    }
}
