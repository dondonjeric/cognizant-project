package com.academy.project;

import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunityAdminAndManagerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@WebMvcTest
public class CommunityAdminAndManagerControllerTest {
    @MockBean
    private CommunityAdminAndManagerServiceImpl communityAdminAndManagerService;


    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    protected void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

//    @Test
//    @DisplayName("" +
//            "Given Payroll with the setup above " +
//            "When updatePayroll(Payroll, Long) is executed " +
//            "Then result should return updated payroll3")
//    public void updateComManager() throws Exception {
//        String url = "/{id}";
//        CommunityAdminAndManager communityAdminAndManager = new CommunityAdminAndManager();
//        communityAdminAndManager.setName("Lyoyd");
//
//       mockMvc.perform(post(url))
//               .contentType(MediaType.)
//    }

}