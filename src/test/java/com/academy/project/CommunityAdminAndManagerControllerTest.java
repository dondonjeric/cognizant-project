package com.academy.project;

import com.academy.project.controller.CommunityAdminAndManagerController;
import com.academy.project.dto.CreateCommunityAdminAndManagerRest;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunityAdminAndManagerService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest

public class CommunityAdminAndManagerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommunityAdminAndManagerService communityAdminAndManagerService;
    @Autowired
    CommunityAdminAndManagerController communityAdminAndManagerController;


    @Test
    public void testAddCommunityAndAdminManager_WhenValidManager_ShouldAddManagerName() throws Exception {

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("Admin");
        request.setEmail("Aj@Gmail.com");
        request.setPassword("admin1234");
        request.setCognizantId("admin1234");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Aj@Gmail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
        .andReturn();
        String content = result.getResponse().getContentAsString();
        String expected ="Successfully registered!";
        assertEquals(expected,content);
    }

}
