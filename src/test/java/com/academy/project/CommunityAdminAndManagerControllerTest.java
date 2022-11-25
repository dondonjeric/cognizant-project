package com.academy.project;

import com.academy.project.controller.CommunityAdminAndManagerController;
import com.academy.project.dto.CreateCommunityAdminAndManagerRest;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunityAdminAndManagerService;
import com.academy.project.validation.Validator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest

public class CommunityAdminAndManagerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommunityAdminAndManagerService communityAdminAndManagerService;


    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_Name_ShouldNotContainInvalidCharacters() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("@ASD");


        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("@ASD");

        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);

        InvalidInputException requestObject = assertThrows(InvalidInputException.class, () -> {
            validator.checkNameIfValid(request.getName());
        });

        InvalidInputException responseObject = assertThrows(InvalidInputException.class, () -> {
            validator.checkNameIfValid(response.getName());
        });

        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))

                .andExpect(result -> assertTrue(responseObject.getMessage().contains("Name should not contain invalid characters!")))
                .andExpect(result -> assertTrue(requestObject.getMessage().contains("Name should not contain invalid characters!")));
    }
}