package com.academy.project.controller;

import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.service.CommunityAdminAndManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CommunityAdminAndManagerControllerDeleteTest {
    @MockBean
    private CommunityAdminAndManagerService communityAdminAndManagerService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deleteCommunityAndAdminManager() throws Exception {
        doNothing().when(communityAdminAndManagerService).deleteCommunityManagerAndAdmin(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/community/manager/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteASoftDeletedCommunityAndAdminManager() throws Exception {
        doThrow(RecordNotFoundException.class).when(communityAdminAndManagerService).deleteCommunityManagerAndAdmin(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/community/manager/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void deleteAnUnknownCommunityAndAdminManager() throws Exception {
        doThrow(RecordNotFoundException.class).when(communityAdminAndManagerService).deleteCommunityManagerAndAdmin(10L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/community/manager/{id}",10L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
