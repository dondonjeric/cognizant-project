package com.academy.project;

import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.academy.project.service.CommunityAdminAndManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
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
    @MockBean
    private CommunityAdminAndManagerRepository communityAdminAndManagerRepository;

    @Test
    public void addCommunityAndAdminManager() throws Exception {

        CommunityAdminAndManager request = new CommunityAdminAndManager();
        request.setName("Manager");
        request.setId(Long.parseLong("2"));
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Manager"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"));

    }
    @Test
    public void updateCommunityAdminAndManager() throws Exception {

        CommunityAdminAndManager request = new CommunityAdminAndManager();
        request.setName("Admin");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(request);
        mockMvc.perform(MockMvcRequestBuilders.put("/community/manager/{id}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Admin"));

    }
}
