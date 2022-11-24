package com.academy.project;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    public void testAddCommunityAndAdminManager_WhenValidManager_ShouldAddManagerName() throws Exception {
        CommunityAdminAndManager request = new CommunityAdminAndManager();
        request.setName("Manager");
        request.setId(2L);
        request.setRoletype("admin");
        request.setEmail("Aj@Gmail.com");
        request.setPassword("admin1234");
        request.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].name").value("Manager"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].email").value("Aj@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].cognizantid").value("admin1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].password").value("admin123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].roletype").value("admin"))
                .andExpect(status().isOk());



    }

}
