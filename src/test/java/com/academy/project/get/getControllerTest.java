package com.academy.project.get;

import com.academy.project.CommunityAdminAndManagerControllerTest;
import com.academy.project.controller.CommunityAdminAndManagerController;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.academy.project.service.CommunityAdminAndManagerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.lang.Boolean.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommunityAdminAndManagerController.class)
public class getControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommunityAdminAndManagerService service;

    @MockBean
    private CommunityAdminAndManagerRepository repository;

    CommunityAdminAndManager entity1 = new CommunityAdminAndManager();
    CommunityAdminAndManager entity2 = new CommunityAdminAndManager();
    CommunityAdminAndManager entity3 = new CommunityAdminAndManager();
    CommunityAdminAndManager entity4 = new CommunityAdminAndManager();




    @Test
    @DisplayName("GIVEN the setup above"+
                "WHEN offset and size is set to null"+
                "THEN result should return all community admin or manager that is ACTIVE")
    public void testGetAll_with_offset_null_and_size_null() throws Exception {
        //entity 1
        entity1.setId(Long.parseLong("1"));
        entity1.setName("Test1");
        entity1.setCognizantId("100123");
        entity1.setEmail("test1@softvision.com");
        entity1.setPassword("1001234");
        entity1.setRoleType("Admin");
        entity1.setIsactive(TRUE);

        //entity 2
        entity2.setId(Long.parseLong("2"));
        entity2.setName("Test2");
        entity2.setCognizantId("200123");
        entity2.setEmail("test2@softvision.com");
        entity2.setPassword("2001234");
        entity2.setRoleType("Admin");
        entity2.setIsactive(TRUE);

        //entity 3
        entity3.setId(Long.parseLong("3"));
        entity3.setName("Test3");
        entity3.setCognizantId("300123");
        entity3.setEmail("test3@softvision.com");
        entity3.setPassword("3001234");
        entity3.setRoleType("Manager");
        entity3.setIsactive(TRUE);

        //entity 4
        entity4.setId(Long.parseLong("4"));
        entity4.setName("Test4");
        entity4.setCognizantId("400123");
        entity4.setEmail("test4@softvision.com");
        entity4.setPassword("4001234");
        entity4.setRoleType("Manager");
        entity4.setIsactive(FALSE);

        when(service.getAllActiveCommunityAdminAndManager(null,null)).thenReturn(List.of(entity1,entity2,entity3,entity4));

        mockMvc.perform(get("/community/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.content[0].id").value("1"))
                .andExpect(jsonPath("$.content[0].name").value("Test1"))
                .andExpect(jsonPath("$.content[0].cognizantId").value("100123"))
                .andExpect(jsonPath("$.content[0].email").value("test1@softvision.com"))
                .andExpect(jsonPath("$.content[0].roleType").value("Admin"))

                .andExpect(jsonPath("$.content[1].id").value("2"))
                .andExpect(jsonPath("$.content[1].name").value("Test2"))
                .andExpect(jsonPath("$.content[1].cognizantId").value("200123"))
                .andExpect(jsonPath("$.content[1].email").value("test2@softvision.com"))
                .andExpect(jsonPath("$.content[1].roleType").value("Admin"))

                .andExpect(jsonPath("$.content[2].id").value("3"))
                .andExpect(jsonPath("$.content[2].name").value("Test3"))
                .andExpect(jsonPath("$.content[2].cognizantId").value("300123"))
                .andExpect(jsonPath("$.content[2].email").value("test3@softvision.com"))
                .andExpect(jsonPath("$.content[2].roleType").value("Manager"))

               /* .andExpect(jsonPath("$.content[3].id").value("4"))
                .andExpect(jsonPath("$.content[3].name").value("Test4"))
                .andExpect(jsonPath("$.content[3].cognizantId").value("400123"))
                .andExpect(jsonPath("$.content[3].email").value("test4@softvision.com"))
                .andExpect(jsonPath("$.content[3].roleType").value("Manager"))*/
                .andDo(print());


    }
}
