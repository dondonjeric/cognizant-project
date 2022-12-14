package com.academy.project.controller;

import com.academy.project.dto.UpdateCommunityAdminAndManagerRest;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunityAdminAndManagerService;
import com.academy.project.helper.Validator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CommunityAdminAndManagerControllerUpdateTest {
    @MockBean
    private CommunityAdminAndManagerService communityAdminAndManagerService;


    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("" +
            "Given CommunityManager with the setup above " +
            "When addComManager(CommunityManager.class) is executed " +
            "Then result should return communityAdminAndManager1")
    public void testGivenUpdatedEmployee_PositiveCase() throws Exception {


        //ARRANGE
        UpdateCommunityAdminAndManagerRest request = new UpdateCommunityAdminAndManagerRest();
        request.setName("von");
        request.setId(1L);
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("von");
        response.setId(1L);
        response.setRoleType("Admin");
        response.setEmail("admin@gmail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");

        //ACT
        when(communityAdminAndManagerService.updateCommunityManagerAndAdmin(any(CommunityAdminAndManager.class))).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(put("/community/manager/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        // ASSERT
        String result = mvcResult.getResponse().getContentAsString();
        String expected = "Successfully updated!";
        assertEquals(expected, result);


    }
    @Test
    public void testGivenUpdatedEmployee_WhenNoGivenName() throws Exception {
        //Act
        UpdateCommunityAdminAndManagerRest request = new UpdateCommunityAdminAndManagerRest();
        request.setName("");
        request.setId(1L);
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("von");
        response.setId(1L);
        response.setRoleType("Admin");
        response.setEmail("admin@gmail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");

        //Arrange
        String expected = "Name is required!";
        when(communityAdminAndManagerService.updateCommunityManagerAndAdmin(any(CommunityAdminAndManager.class)))
                .thenThrow(new InvalidInputException(expected));
        //Assert
        mockMvc.perform(put("/community/manager/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();


    }
    @Test
    public void testGivenUpdatedEmployee_WhenItHasLessThanMinimumCharacters() throws Exception {
        //ACT
        UpdateCommunityAdminAndManagerRest request = new UpdateCommunityAdminAndManagerRest();
        request.setName("A");
        request.setId(1L);
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("von");
        response.setId(1L);
        response.setRoleType("Admin");
        response.setEmail("admin@gmail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");

        //ARRANGE
        String expected ="Name length should exceed 2 characters!";
        when(communityAdminAndManagerService.updateCommunityManagerAndAdmin(any(CommunityAdminAndManager.class))).thenThrow(new InvalidInputException(expected));
        //Assert
        mockMvc.perform(put("/community/manager/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();





    }
    @Test
    public void testGivenUpdatedEmployee_WhenLengthExceeds100() throws Exception {
        //Act
        Validator validator = new Validator();
        UpdateCommunityAdminAndManagerRest request = new UpdateCommunityAdminAndManagerRest();
        //103 letters
        request.setName("askdjasladaksdlaaksdalaskdalaskdalaksaldkasaldkasaldkasaldkasladaklsfnakjdfbakjsbaskjbckajbsckjabsckajb");
        request.setId(1L);
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("von");
        response.setId(1L);
        response.setRoleType("Admin");
        response.setEmail("admin@gmail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");

        //Arrange
        String expected ="Name length should not exceed 100 characters!";
        when(communityAdminAndManagerService.updateCommunityManagerAndAdmin(any(CommunityAdminAndManager.class))).thenThrow(new InvalidInputException(expected));
        //Assert
        mockMvc.perform(put("/community/manager/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

    }
    @Test
    public void testGivenUpdatedEmployee_WhenSpecialCharactersAreInput() throws Exception {
        Validator validator = new Validator();
        UpdateCommunityAdminAndManagerRest request = new UpdateCommunityAdminAndManagerRest();
        //Act
        request.setName("*&!Alexander");
        request.setId(1L);
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("von");
        response.setId(1L);
        response.setRoleType("Admin");
        response.setEmail("admin@gmail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");

        //Arrange
        String expected ="Name should not contain invalid characters!";
        when(communityAdminAndManagerService.updateCommunityManagerAndAdmin(any(CommunityAdminAndManager.class))).thenThrow(new InvalidInputException(expected));
        //Assert
        mockMvc.perform(put("/community/manager/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();


    }
    @Test
    public void testGivenUpdatedEmployee_WhenNameInput_DoesNotMatchName() throws Exception {
        //Act
        Validator validator = new Validator();
        UpdateCommunityAdminAndManagerRest request = new UpdateCommunityAdminAndManagerRest();
        //103 letters
        request.setName("_don");
        request.setId(1L);
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("von");
        response.setId(1L);
        response.setRoleType("Admin");
        response.setEmail("admin@gmail.com");
        response.setPassword("admin1234");
        response.setCognizantId("admin1234");

        //Arrange
        String expected ="Name should not contain invalid characters!";
        when(communityAdminAndManagerService.updateCommunityManagerAndAdmin(any(CommunityAdminAndManager.class))).thenThrow(new InvalidInputException(expected));
        //Assert
        mockMvc.perform(put("/community/manager/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
