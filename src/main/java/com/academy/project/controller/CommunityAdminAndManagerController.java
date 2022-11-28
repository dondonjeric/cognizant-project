package com.academy.project.controller;

import com.academy.project.dto.CreateCommunityAdminAndManagerRest;
import com.academy.project.dto.DeleteCommunityAdminAndManagerRest;
import com.academy.project.dto.GetActiveCommunityAdminAndManagerRest;
import com.academy.project.dto.UpdateCommunityAdminAndManagerRest;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunityAdminAndManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping
    private ResponseEntity<String> deleteCommunityAdminAndManager(@RequestParam Long id, DeleteCommunityAdminAndManagerRest deleteManager) throws RecordNotFoundException {
        CommunityAdminAndManager manager = modelMapper.map(deleteManager, CommunityAdminAndManager.class);
        service.deleteCommunityManagerAndAdmin(manager);
        return new ResponseEntity<>("Successfully deleted!", HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<String> addCommunityAndAdminManager(@RequestBody CreateCommunityAdminAndManagerRest create) throws InvalidInputException, RecordNotFoundException {
        CommunityAdminAndManager manager = modelMapper.map(create, CommunityAdminAndManager.class);
        service.addCommunityAdminAndManager(manager);
        return new ResponseEntity<>("Successfully registered!", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<GetActiveCommunityAdminAndManagerRest>> getAllActiveCommunityAdminAndManager(Pageable pageable){
        Page<CommunityAdminAndManager> manager = service.getAllActiveManagerAndAdmin(pageable);
        List<GetActiveCommunityAdminAndManagerRest> testGet = manager.stream().map(communityAdminAndManager -> modelMapper.map(communityAdminAndManager, GetActiveCommunityAdminAndManagerRest.class)).toList();
        return new ResponseEntity<>(new PageImpl<>(testGet,pageable,testGet.size()),HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadExcel(){
        String filename = "CommunityAdminAndManager.xlsx";
        InputStreamResource file = new InputStreamResource(service.downloadExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }



}
